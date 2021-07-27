package cleanArch.useCase_package.service

abstract class addItemService{
  def addItem(id: Int, text: String, state: Boolean): Unit
}

object addItemService