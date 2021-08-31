package cleanArch.contract.callback.todo

import cleanArch.domain.todo.Item

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

abstract class ItemCallback {

  def addItemCallback(userId: Long, text: String)(implicit ec: ExecutionContext): Future[Int]

  def getItemCallback(id: Long)(implicit ec: ExecutionContext): Future[Option[Item]]

  def updateItemCallback(id: Long, item: Item)(implicit ec: ExecutionContext): Future[Int]

  def removeItemCallback(id: Long)(implicit ec: ExecutionContext): Future[Unit]

}
