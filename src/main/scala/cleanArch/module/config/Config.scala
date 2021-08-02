package cleanArch.module.config

import cleanArch.contract.service.todo._
import cleanArch.domain.todo.Item
import cleanArch.module.database.Holder
import cleanArch.application.usecase.todo._

sealed abstract class Config {

  val db: Holder
  val addItemService: AddItemService
  val editItemService: EditItemService
  val getItemService: GetItemService

  def addItem(message: String, state: Boolean): Unit = {
    addItemService.call(AddItemService.Request(message, state))
  }

  def getItem(id: Int): Option[Item] = {
    getItemService.call(GetItemService.Request(id))
  }

  def editItem(id: Int, field: String, text: String): Unit = {
    editItemService.call(EditItemService.Request(id, field, text))
  }
}


object Config {
  class ConfigOne extends Config {

    override val db: Holder = Holder()
    override val addItemService: AddItemService = AddItemUseCase(db)
    override val editItemService: EditItemService = EditItemUseCase(db)
    override val getItemService: GetItemService = GetItemUseCase(db)

  }

  object ConfigOne extends ConfigOne
}
