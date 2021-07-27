package cleanArch.useCase_package.useCase

import cleanArch.database._
import cleanArch.useCase_package.service._
import cleanArch.repository_package.repoImpel.GetItemImpel

class GetItemUseCase(db: Holder) extends GetItemService {

  override def getItem(id: Int): Items = {
    val rep = GetItemImpel(db)
    rep.getItemInRepo(id)
  }
}

object GetItemUseCase{
  def apply(db: Holder): GetItemUseCase = new GetItemUseCase(db)
}
