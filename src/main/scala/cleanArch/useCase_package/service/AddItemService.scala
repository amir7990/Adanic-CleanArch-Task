package cleanArch.useCase_package.service

abstract class AddItemService{
  def addItem(id: Int, text: String, state: Boolean): Unit
}

object AddItemService