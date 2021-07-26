package cleanArch.useCases.Service

import cleanArch.DB.Items

abstract class getItemService {
  def getItem(id: Int): Items
}

object getItemService