package cleanArch.module.database

import cleanArch.domain.todo.Item

class Holder {

  var itemMap: Map[Int, Item] = Map.empty

  def addItem(item: Item): Unit = {
    itemMap = itemMap + (item.id -> item)
  }

  def getItem(id: Int): Option[Item] = {
    itemMap.get(id)
  }

  def editItem(id: Int, field: String, text: String): Unit = {
    val item = itemMap.getOrElse(id, throw new NoSuchElementException())
    val newItem = field match {
      case "message" => item.copy(message = text)
      case "done" =>
        val done = text == "true"
        item.copy(done = done)
      case _ => throw new NoSuchFieldException()
    }
    itemMap = itemMap + (item.id -> newItem)
  }
}

object Holder {
  def apply(): Holder = new Holder
}