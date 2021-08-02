package cleanArch.application.usecase.todo

import cleanArch.application.repository.todo.GetItemImpel
import cleanArch.contract.service.todo.GetItemService
import cleanArch.domain.todo.Item
import cleanArch.module.database.Holder

class GetItemUseCase(database: Holder) extends GetItemService {
  override def call(request: GetItemService.Request): Option[Item] = {
    val rep = GetItemImpel(database)
    rep.getItemInRepo(request.id)
  }
}

object GetItemUseCase {
  def apply(database: Holder): GetItemUseCase = new GetItemUseCase(database: Holder)
}
