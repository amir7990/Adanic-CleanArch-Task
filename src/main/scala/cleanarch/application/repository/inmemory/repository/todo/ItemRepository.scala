package cleanarch.application.repository.inmemory.repository.todo

import cleanarch.contract.callback.todo.ItemCallback
import cleanarch.domain.todo.Item
import cleanarch.module.inmemory.InMemoryModule

import scala.annotation.tailrec
import scala.concurrent.Future

class ItemRepository extends ItemCallback with InMemoryModule[Map[Int, Item]] {

  override def addItemCallback(userId: Long, text: String, state: Boolean): Future[Map[Int, Item]] = {
    @tailrec
    def findNewId(map: Map[Int, _], init: Int): Int = {
      if (map.contains(init)) findNewId(map, init + 1) else init
    }

    val oldItemOption = data.find(key => key._1 == userId)
    val itemMap = oldItemOption match {
      case None => Map[Int, Item]()
      case Some(map) => map._2
    }
    val itemId = findNewId(itemMap, 1)
    val item = Item(itemId, userId,  text, state)
    val newITemMap = itemMap + (itemId -> item)
    updateElement(userId, newITemMap)
  }

  override def getItemCallback(id: Long): Future[Option[Map[Int, Item]]] = {
    getElement(id)
  }

  override def updateItemCallback(id: Long, itemMap: Map[Int, Item]): Future[Map[Int, Item]] = {
    updateElement(id, itemMap)
  }

  override def removeItemCallback(id: Long): Future[Unit] = {
    removeElement(id)
  }

}

object ItemRepository extends ItemRepository
