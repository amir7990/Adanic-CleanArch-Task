package cleanArch

import cleanArch.application.usecase._
import cleanArch.module._

class Main

object Main{
  def main(args: Array[String]): Unit = {
    val db = Holder()
    val add = AddItemUseCase(db)
    val get = GetItemUseCase(db)
    val edit = EditItemUseCase(db)
    add.addItem(1, "hello world", state = true)
    println(get.getItem(1).message)
    edit.editItem(1, "state", "false")
    println(get.getItem(1).Done)
  }
}

