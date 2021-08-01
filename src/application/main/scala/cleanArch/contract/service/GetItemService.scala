package cleanArch.contract.service

import cleanArch.application.repository.GetItemImpel
import cleanArch.domain.entity.Items

abstract class GetItemService {

  val rep: GetItemImpel

  def getItem(id: Int): Items
}

object GetItemService