package cleanarch.application.repository.inmemory.repository.todo

import cleanarch.contract.callback.todo.ItemCallback
import cleanarch.domain.todo.Item
import cleanarch.module.inmemory.InMemoryModule

import scala.concurrent.Future

class ItemRepository extends ItemCallback with InMemoryModule[Item] {

  override val name: String = "Item"

  override def addItemCallback(userId: Long, text: String, state: Boolean): Future[Item] = {
    val item = Item(createId(), userId, text, state)
    addElement(item)
  }

  override def getItemCallback(id: Long): Future[Option[Item]] = Future {
    data.find(_.id == id)
  }

  override def updateItemCallback(item: Item): Future[Unit] = {
    updateElement(item, i => i.id == item.id)
  }

  override def removeItemCallback(id: Long): Future[Unit] = {
    removeElement(i => i.id == id)
  }

}

object ItemRepository extends ItemRepository
