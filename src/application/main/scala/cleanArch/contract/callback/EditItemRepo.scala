package cleanArch.contract.callback

import cleanArch.module.Holder

abstract class EditItemRepo {

  val db: Holder

  def editItemInRepo(id: Int, field: String, text: String): Unit

}

object EditItemRepo