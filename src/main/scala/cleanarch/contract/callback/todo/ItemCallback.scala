package cleanarch.contract.callback.todo

import cleanarch.domain.todo.Item

import scala.concurrent.Future

abstract class ItemCallback {

  def addItemCallback(userId: Long, text: String, state: Boolean): Future[Item]

  def getItemCallback(id: Long): Future[Option[Item]]

  def updateItemCallback(item: Item): Future[Unit]

  def removeItemCallback(id: Long): Future[Unit]

}
