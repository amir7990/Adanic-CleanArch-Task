package cleanArch.contract.callback.todo

import cleanArch.module.database.Holder

abstract class EditItemRepo {

  val db: Holder

  def editItemInRepo(id: Int, field: String, text: String): Unit

}