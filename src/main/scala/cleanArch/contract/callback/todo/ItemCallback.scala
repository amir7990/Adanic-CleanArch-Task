package cleanArch.contract.callback.todo

import cleanArch.domain.todo.Item

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

abstract class ItemCallback {

  def addItemCallback(userId: Long, text: String, state: Boolean)(implicit ec: ExecutionContext): Future[Map[Int, Item]]

  def getItemCallback(id: Long)(implicit ec: ExecutionContext): Future[Option[Map[Int, Item]]]

  def updateItemCallback(id: Long, itemMap: Map[Int, Item])(implicit ec: ExecutionContext): Future[Map[Int, Item]]

  def removeItemCallback(id: Long)(implicit ec: ExecutionContext): Future[Unit]

}
