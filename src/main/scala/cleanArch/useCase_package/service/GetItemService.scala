package cleanArch.useCase_package.service

import cleanArch.database.Items

abstract class GetItemService {
  def getItem(id: Int): Items
}

object GetItemService