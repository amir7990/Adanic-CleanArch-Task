package cleanArch.useCases.Service

abstract class getItemService {
  def getItem(id: Int): String
}

object getItemService