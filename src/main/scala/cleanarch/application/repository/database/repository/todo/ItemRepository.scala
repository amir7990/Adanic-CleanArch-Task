package cleanarch.application.repository.database.repository.todo

import cleanarch.contract.callback.todo.ItemCallback
import cleanarch.domain.todo.Item
import cleanarch.module.database.DatabaseModule
import cleanarch.module.database.DatabaseModule.cleanArchDatabase
import scalikejdbc._

import scala.concurrent.{ExecutionContext, Future}

class ItemRepository extends ItemCallback with DatabaseModule {

  override def addItemCallback(userId: Long, text: String, state: Boolean): Future[Map[Int, Item]] = ???

  override def getItemCallback(id: Long): Future[Option[Map[Int, Item]]] = ???

  override def updateItemCallback(id: Long, itemMap: Map[Int, Item]): Future[Map[Int, Item]] = ???

  override def removeItemCallback(id: Long): Future[Unit] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           DELETE FROM Item WHERE id = $id
         """.update().apply()
    }
  }

}

object ItemRepository extends ItemRepository
