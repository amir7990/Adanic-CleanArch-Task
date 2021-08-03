package cleanArch.application.usecase.todo

import cleanArch.application.repository.auth.UserRepository
import cleanArch.application.repository.todo.GetItemImpel
import cleanArch.contract.service.todo.GetItemService
import cleanArch.domain.todo.Item
import cleanArch.module.database.Holder

class GetItemUseCase(database: Holder) extends GetItemService {
  override def call(request: GetItemService.Request): Option[Item] = {
    val itemRep = GetItemImpel(database)
    val userRep = UserRepository(database)
    val session = userRep.getSession(request.username)
    session match {
      case None => throw new NoSuchElementException()
      case Some(session) =>
        if (session.isLogin) {
          itemRep.getItemInRepo(request.id)
        } else {
          throw new NoSuchElementException()
        }
    }

  }
}

object GetItemUseCase {
  def apply(database: Holder): GetItemUseCase = new GetItemUseCase(database: Holder)
}
