package cleanArch.useCase_package.useCase

import cleanArch.useCase_package.service.editItemService
import cleanArch.repository_package.repoImpel._
import cleanArch.database._

class editItemUseCase(db: Holder) extends editItemService {
  override def editItem(id: Int, field: String, text: String): Unit = {
    val rep = editItemImpel(db)
    rep.editItemInRepo(id, field, text)
  }
}

object editItemUseCase{
  def apply(db: Holder): editItemUseCase = new editItemUseCase(db)
}
