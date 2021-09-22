package cleanarch.application.repository.inmemory.repository.todo

import cleanarch.contract.callback.todo.ItemCallback
import cleanarch.domain.todo.Item
import cleanarch.module.inmemory.InMemoryModule

import scala.concurrent.Future

class ItemRepository extends ItemCallback with InMemoryModule[Item] {

  override def addItemCallback(userId: Long, text: String, state: Boolean): Future[Item] = {
    val id = lastId
    val item = Item(id, userId, text, state)
    addElement(item)
  }

  override def getItemCallback(id: Long): Future[Option[Item]] = {
    getElement(id)
  }

  override def updateItemCallback(id: Long, item: Item): Future[Unit] = {
    updateElement(id, item)
  }

  override def removeItemCallback(id: Long): Future[Unit] = {
    removeElement(id)
  }

}

object ItemRepository extends ItemRepository
