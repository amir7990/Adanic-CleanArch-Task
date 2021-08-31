package cleanArch.application.repository.inmemory.repository.todo

import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.domain.todo.Item
import cleanArch.module.inmemory.InMemoryModule

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class ItemRepository extends ItemCallback with InMemoryModule[Item] {

  override def addItemCallback(userId: Long, text: String)(implicit ec: ExecutionContext): Future[Int] = {
    synchronized {
      val id = lastId
      val item = Item(id, userId, text, done = false)
      addElement(item)
      Future{ id.toInt }
    }
  }

  override def getItemCallback(id: Long)(implicit ec: ExecutionContext): Future[Option[Item]] = {
    getElement(id)
  }

  override def updateItemCallback(id: Long, item: Item)(implicit ec: ExecutionContext): Future[Int] = {
    updateElement(id, item)
    Future{ id.toInt }
  }

  override def removeItemCallback(id: Long)(implicit ec: ExecutionContext): Future[Unit] = {
    removeElement(id)
  }

}

object ItemRepository extends ItemRepository
