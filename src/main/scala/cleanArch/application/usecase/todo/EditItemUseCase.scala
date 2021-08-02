package cleanArch.application.usecase.todo

import cleanArch.application.repository.todo.EditItemImpel
import cleanArch.contract.service.todo.EditItemService
import cleanArch.module.database.Holder

class EditItemUseCase(database: Holder) extends EditItemService {
  override def call(request: EditItemService.Request): Unit = {
    val rep = EditItemImpel(database)
    rep.editItemInRepo(request.id, request.field, request.text)
  }
}

object EditItemUseCase {
  def apply(database: Holder): EditItemUseCase = new EditItemUseCase(database: Holder)
}
