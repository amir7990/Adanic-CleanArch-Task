package cleanArch.contract.service

import cleanArch.application.repository.AddItemImpel

abstract class AddItemService{

  val rep: AddItemImpel

  def addItem(id: Int, text: String, state: Boolean): Unit
}

object AddItemService