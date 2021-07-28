package cleanArch.useCase_package.useCase

import cleanArch.useCase_package.service.EditItemService
import cleanArch.repository_package.repoImpel._
import cleanArch.database._

class EditItemUseCase(db: Holder) extends EditItemService {

  override val rep: EditItemImpel = EditItemImpel(db)

  override def editItem(id: Int, field: String, text: String): Unit = {
    rep.editItemInRepo(id, field, text)
  }
}

object EditItemUseCase{
  def apply(db: Holder): EditItemUseCase = new EditItemUseCase(db)
}
