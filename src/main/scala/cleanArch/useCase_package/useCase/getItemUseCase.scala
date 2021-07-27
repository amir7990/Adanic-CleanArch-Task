package cleanArch.useCase_package.useCase

import cleanArch.database._
import cleanArch.useCase_package.service._
import cleanArch.repository_package.repoImpel.getItemImpel

class getItemUseCase(db: Holder) extends getItemService {

  override def getItem(id: Int): Items = {
    val rep = getItemImpel(db)
    rep.getItemInRepo(id)
  }
}

object getItemUseCase{
  def apply(db: Holder): getItemUseCase = new getItemUseCase(db)
}
