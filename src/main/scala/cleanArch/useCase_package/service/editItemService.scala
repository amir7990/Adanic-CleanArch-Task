package cleanArch.useCase_package.service

abstract class editItemService {
  def editItem(id: Int, field: String, text: String): Unit
}

object editItemService