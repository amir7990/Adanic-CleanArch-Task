package cleanArch.contract.callback.todo

import cleanArch.module.database.Holder

abstract class AddItemRepo {

  val db: Holder

  def addItemInRepo(id: Int, text: String, state: Boolean): Unit

}