package cleanArch.application.usecase

import cleanArch.application.repository.GetItemImpel
import cleanArch.contract.service.GetItemService
import cleanArch.domain.entity.Items
import cleanArch.module.Holder

class GetItemUseCase(database: Holder) extends GetItemService {

  override val rep: GetItemImpel = GetItemImpel(database)

  override def getItem(id: Int): Items = {
    rep.getItemInRepo(id)
  }
}

object GetItemUseCase{
  def apply(database: Holder): GetItemUseCase = new GetItemUseCase(database: Holder)
}
