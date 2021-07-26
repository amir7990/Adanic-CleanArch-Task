package cleanArch.useCases.Service

abstract class addItemService{
  def addItem(id: Int, text: String, state: Boolean): Unit
}

object addItemService