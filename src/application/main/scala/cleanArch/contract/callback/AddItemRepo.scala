package cleanArch.contract.callback

import cleanArch.module.Holder

abstract class AddItemRepo {

  val db: Holder

  def addItemInRepo(id: Int, text: String, state: Boolean): Unit

}

object AddItemRepo