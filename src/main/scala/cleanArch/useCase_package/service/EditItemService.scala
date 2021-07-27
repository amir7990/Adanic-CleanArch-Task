package cleanArch.useCase_package.service

abstract class EditItemService {
  def editItem(id: Int, field: String, text: String): Unit
}

object EditItemService