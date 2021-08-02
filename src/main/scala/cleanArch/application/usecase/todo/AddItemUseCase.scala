package cleanArch.application.usecase.todo

import cleanArch.application.repository.todo.AddItemImpel
import cleanArch.contract.service.todo.AddItemService
import cleanArch.module.database.Holder

class AddItemUseCase(database: Holder) extends AddItemService {
  override def call(request: AddItemService.Request): Unit = {
    val rep = AddItemImpel(database)
    rep.addItemInRepo(request.text, request.state)
  }
}

object AddItemUseCase {
  def apply(database: Holder): AddItemUseCase = new AddItemUseCase(database: Holder)
}
