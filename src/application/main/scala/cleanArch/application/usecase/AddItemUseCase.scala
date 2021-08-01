package cleanArch.application.usecase

import cleanArch.application.repository.AddItemImpel
import cleanArch.contract.service.AddItemService
import cleanArch.module.Holder

class AddItemUseCase(database: Holder) extends AddItemService {

  override val rep: AddItemImpel = AddItemImpel(database)

  override def addItem(id: Int, text: String, state: Boolean): Unit = {
    rep.addItemInRepo(id, text, state)
  }
}

object AddItemUseCase{
  def apply(database: Holder): AddItemUseCase = new AddItemUseCase(database: Holder)
}
