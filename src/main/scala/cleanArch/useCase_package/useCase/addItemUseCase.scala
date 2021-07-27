package cleanArch.useCase_package.useCase

import cleanArch.useCase_package.service.addItemService
import cleanArch.repository_package.repoImpel._
import cleanArch.database._

class addItemUseCase(db: Holder) extends addItemService {
  override def addItem(id: Int, text: String, state: Boolean): Unit = {
    val rep = addItemImpel(db)
    rep.addItemInRepo(id, text, state)
  }
}

object addItemUseCase{
  def apply(db: Holder): addItemUseCase = new addItemUseCase(db)
}
