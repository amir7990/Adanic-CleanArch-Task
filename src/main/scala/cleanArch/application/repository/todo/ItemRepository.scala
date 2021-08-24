package cleanArch.application.repository.todo

import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.domain.todo.Item
import cleanArch.module.database.DatabaseModule

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class ItemRepository extends ItemCallback with DatabaseModule[Map[Int, Item]] {

  override def addItemCallback(userId: Long, text: String, state: Boolean)(implicit ec: ExecutionContext): Future[Map[Int, Item]] = {
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
    val item = Item(itemId, text, state)
    val newITemMap = itemMap + (itemId -> item)
    updateElement(userId, newITemMap)
  }

  override def getItemCallback(id: Long)(implicit ec: ExecutionContext): Future[Option[Map[Int, Item]]] = {
    getElement(id)
  }

  override def updateItemCallback(id: Long, itemMap: Map[Int, Item])(implicit ec: ExecutionContext): Future[Map[Int, Item]] = {
    updateElement(id, itemMap)
  }

  override def removeItemCallback(id: Long)(implicit ec: ExecutionContext): Future[Unit] = {
    removeElement(id)
  }

}

object ItemRepository extends ItemRepository
