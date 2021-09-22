package cleanarch.application.repository.database.repository.todo

import cleanarch.application.repository.database.adapter.todo.ItemAdapter
import cleanarch.contract.callback.todo.ItemCallback
import cleanarch.domain.todo.Item
import cleanarch.module.database.DatabaseModule
import cleanarch.module.database.DatabaseModule.cleanArchDatabase
import scalikejdbc._

import scala.concurrent.Future

class ItemRepository extends ItemCallback with DatabaseModule {

  override def addItemCallback(userId: Long, text: String, state: Boolean): Future[Item] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      val id = sql"""
           INSERT INTO items
           (userid, message) VALUES ($userId, $text)
         """.updateAndReturnGeneratedKey().apply()
      Item(id, userId, text, done = false)
    }
  }

  override def getItemCallback(id: Long): Future[Option[Item]] = Future {
    NamedDB(cleanArchDatabase) readOnly { implicit session =>
      sql"""
          SELECT * FROM items
          WHERE id = $id
          """.map(ItemAdapter.item).single().apply()
    }
  }

  override def updateItemCallback(id: Long, item: Item): Future[Unit] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      val successor = sql"""
           UPDATE items SET (message = ${item.message}, done = ${item.done})
           WHERE id = $id
         """.update().apply()
      successor match {
        case 1 => Future successful Unit
        case 0 => Future failed new Exception(s"Item Not Found")
        case _ => Future failed new Exception(s"Internal Server Error")
      }
    }
  }

  override def removeItemCallback(id: Long): Future[Unit] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           DELETE FROM items WHERE id = $id
         """.update().apply()
    }
  }

}

object ItemRepository extends ItemRepository
