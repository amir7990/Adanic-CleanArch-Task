package cleanArch.useCase_package.service

import cleanArch.repository_package.repoImpel._
import cleanArch.entity.Items

abstract class GetItemService {

  val rep: GetItemImpel

  def getItem(id: Int): Items
}

object GetItemService