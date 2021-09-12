package cleanarch.contract.callback.todo

import cleanarch.domain.todo.Item

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

abstract class ItemCallback {

  def addItemCallback(userId: Long, text: String, state: Boolean): Future[Map[Int, Item]]

  def getItemCallback(id: Long): Future[Option[Map[Int, Item]]]

  def updateItemCallback(id: Long, itemMap: Map[Int, Item]): Future[Map[Int, Item]]

  def removeItemCallback(id: Long): Future[Unit]

}
