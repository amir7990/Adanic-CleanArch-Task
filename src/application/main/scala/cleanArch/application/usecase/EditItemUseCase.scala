package cleanArch.application.usecase

import cleanArch.application.repository.EditItemImpel
import cleanArch.contract.service.EditItemService
import cleanArch.module.Holder

class EditItemUseCase(database: Holder) extends EditItemService {

  override val rep: EditItemImpel = EditItemImpel(database)

  override def editItem(id: Int, field: String, text: String): Unit = {
    rep.editItemInRepo(id, field, text)
  }
}

object EditItemUseCase{
  def apply(database: Holder): EditItemUseCase = new EditItemUseCase(database: Holder)
}
