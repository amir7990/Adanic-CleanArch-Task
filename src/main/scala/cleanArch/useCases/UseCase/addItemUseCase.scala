package cleanArch.useCases.UseCase

import cleanArch.useCases.Service.addItemService
import cleanArch.repositories.RepoImpel._
import cleanArch.DB._

class addItemUseCase(db: Holder) extends addItemService {
  override def addItem(id: Int, text: String, state: Boolean): Unit = {
    val rep = addItemImpel(db)
    rep.addItemInRepo(id, text, state)
  }
}

object addItemUseCase{
  def apply(db: Holder): addItemUseCase = new addItemUseCase(db)
}
