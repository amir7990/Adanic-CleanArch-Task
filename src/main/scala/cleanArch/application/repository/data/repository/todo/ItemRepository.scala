package cleanArch.application.repository.data.repository.todo

import cleanArch.application.repository.data.adapter.Adapter
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.domain.todo.Item
import cleanArch.module.database.DatabaseModule
import cleanArch.module.database.DatabaseModule.cleanArchDatabase
import scalikejdbc._

import scala.concurrent.{ExecutionContext, Future}

class ItemRepository extends ItemCallback with DatabaseModule {

  override def addItemCallback(userId: Long, text: String)(implicit ec: ExecutionContext): Future[Int] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           INSERT INTO Item
           (userId, message) VALUES ($userId, $text)
         """.updateAndReturnGeneratedKey().apply().toInt
    }
  }

  override def getItemCallback(id: Long)(implicit ec: ExecutionContext): Future[Option[Item]] = Future {
    NamedDB(cleanArchDatabase) readOnly { implicit session =>
      sql"""
           SELECT * FROM Item
           WHERE id = $id
         """.map(Adapter.item).single().apply()
    }
  }

  override def updateItemCallback(id: Long, item: Item)(implicit ec: ExecutionContext): Future[Int] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           UPDATE Item SET (message = ${item.message}, done = ${item.done})
           WHERE id = $id
         """.update().apply()
    }
  }

  override def removeItemCallback(id: Long)(implicit ec: ExecutionContext): Future[Unit] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           DELETE FROM Item WHERE id = $id
         """.update().apply()
    }
  }

}

object ItemRepository extends ItemRepository
