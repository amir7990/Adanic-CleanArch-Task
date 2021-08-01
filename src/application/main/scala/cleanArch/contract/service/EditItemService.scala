package cleanArch.contract.service

import cleanArch.application.repository.EditItemImpel

abstract class EditItemService {

  val rep: EditItemImpel

  def editItem(id: Int, field: String, text: String): Unit
}

object EditItemService