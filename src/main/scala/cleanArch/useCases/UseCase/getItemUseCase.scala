package cleanArch.useCases.UseCase

import cleanArch.DB._
import cleanArch.useCases.Service._
import cleanArch.repositories.RepoImpel.getItemImpel

class getItemUseCase(db: Holder) extends getItemService {

  override def getItem(id: Int): Items = {
    val rep = getItemImpel(db)
    rep.getItemInRepo(id)
  }
}

object getItemUseCase{
  def apply(db: Holder): getItemUseCase = new getItemUseCase(db)
}
