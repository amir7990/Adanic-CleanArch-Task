package cleanArch.application.usecase.todo

import cleanArch.application.repository.auth.UserRepository
import cleanArch.application.repository.todo.AddItemImpel
import cleanArch.contract.service.todo.AddItemService
import cleanArch.module.database.Holder

class AddItemUseCase(database: Holder) extends AddItemService {
  override def call(request: AddItemService.Request): Unit = {
    val itemRep = AddItemImpel(database)
    val userRep = UserRepository(database)
    val session = userRep.getSession(request.username)
    session match {
      case None => throw new Exception(s"User is Not Signed Up!")
      case Some(session) =>
        if (session.isLogin) {
          itemRep.addItemInRepo(request.text, request.state)
        }
        else {
          throw new Exception(s"User is Not Signed In!")
        }
    }

  }
}

object AddItemUseCase {
  def apply(database: Holder): AddItemUseCase = new AddItemUseCase(database: Holder)
}
