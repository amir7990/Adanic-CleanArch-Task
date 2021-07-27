package cleanArch

import cleanArch.useCase_package.useCase._
import cleanArch.database._

class Main

object Main{
  def main(args: Array[String]): Unit = {
    val db = Holder()
    val add = addItemUseCase(db)
    val get = getItemUseCase(db)
    val edit = editItemUseCase(db)
    add.addItem(1, "hello world", state = true)
    println(get.getItem(1).message)
    edit.editItem(1, "state", "false")
    println(get.getItem(1).Done)
    println(db.items.size)
  }
}


