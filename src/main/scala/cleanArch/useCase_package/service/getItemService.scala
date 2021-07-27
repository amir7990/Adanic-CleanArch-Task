package cleanArch.useCase_package.service

import cleanArch.database.Items

abstract class getItemService {
  def getItem(id: Int): Items
}

object getItemService