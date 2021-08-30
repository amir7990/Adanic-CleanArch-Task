package cleanArch.application.repository.data.repository.todo

import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.domain.todo.Item
import cleanArch.module.database.DatabaseModule
import cleanArch.module.database.DatabaseModule.cleanArchDatabase
import scalikejdbc._

import scala.concurrent.{ExecutionContext, Future}

class ItemRepository extends ItemCallback with DatabaseModule {

  override def addItemCallback(userId: Long, text: String, state: Boolean)(implicit ec: ExecutionContext): Future[Map[Int, Item]] = ???

  override def getItemCallback(id: Long)(implicit ec: ExecutionContext): Future[Option[Map[Int, Item]]] = ???

  override def updateItemCallback(id: Long, itemMap: Map[Int, Item])(implicit ec: ExecutionContext): Future[Map[Int, Item]] = ???

  override def removeItemCallback(id: Long)(implicit ec: ExecutionContext): Future[Unit] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           DELETE FROM Item WHERE id = $id
         """.update().apply()
    }
  }

}
