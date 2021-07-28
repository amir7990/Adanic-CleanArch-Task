package cleanArch.useCase_package.service

import cleanArch.repository_package.repoImpel._

abstract class AddItemService{

  val rep: AddItemImpel

  def addItem(id: Int, text: String, state: Boolean): Unit
}

object AddItemService