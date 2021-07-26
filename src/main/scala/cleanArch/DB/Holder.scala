package cleanArch.DB

class Holder {

  var items: Map[Int, Items] = Map.empty

  def addItem(item: Items): Unit ={
    val temp = Map(item.id -> item)
    val newItems = items++temp
    items = newItems
  }

  def getItem(id: Int) : Items = items(id)

}

object Holder{
  def apply: Holder = new Holder
}