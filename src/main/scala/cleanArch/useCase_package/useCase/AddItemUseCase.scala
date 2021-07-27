package cleanArch.useCase_package.useCase

import cleanArch.useCase_package.service.AddItemService
import cleanArch.repository_package.repoImpel._
import cleanArch.database._

class AddItemUseCase(db: Holder) extends AddItemService {
  override def addItem(id: Int, text: String, state: Boolean): Unit = {
    val rep = AddItemImpel(db)
    rep.addItemInRepo(id, text, state)
  }
}

object AddItemUseCase{
  def apply(db: Holder): AddItemUseCase = new AddItemUseCase(db)
}
