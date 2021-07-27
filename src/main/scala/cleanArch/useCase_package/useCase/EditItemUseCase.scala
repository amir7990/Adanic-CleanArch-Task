package cleanArch.useCase_package.useCase

import cleanArch.useCase_package.service.EditItemService
import cleanArch.repository_package.repoImpel._
import cleanArch.database._

class EditItemUseCase(db: Holder) extends EditItemService {
  override def editItem(id: Int, field: String, text: String): Unit = {
    val rep = EditItemImpel(db)
    rep.editItemInRepo(id, field, text)
  }
}

object EditItemUseCase{
  def apply(db: Holder): EditItemUseCase = new EditItemUseCase(db)
}
