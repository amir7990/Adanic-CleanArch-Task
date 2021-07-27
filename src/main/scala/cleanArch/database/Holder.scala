package cleanArch.database

class Holder {

  private var items: Map[Int, Items] = Map.empty

  def addItem(item: Items): Unit ={
    val temp = Map(item.id -> item)
    val newItems = items++temp
    items = newItems
  }

  def getItem(id: Int) : Items = items(id)

  def editItem(id: Int, field: String, text: String): Unit = {
    val item = items(id)
    if (field == "message"){
      item.message = text
      val newItem = Map(item.id -> item)
      val temp = items ++ newItem
      items = temp
    }else{
      if (text == "true") {
        item.Done = true
      }else{
        item.Done = false
      }
      val newItem = Map(item.id -> item)
      val temp = items ++ newItem
      items = temp
    }
  }

}

object Holder{
  def apply(): Holder = new Holder
}