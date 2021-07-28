package cleanArch.useCase_package.service

import cleanArch.repository_package.repoImpel._

abstract class EditItemService {

  val rep: EditItemImpel

  def editItem(id: Int, field: String, text: String): Unit
}

object EditItemService