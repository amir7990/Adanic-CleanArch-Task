package cleanArch.application.usecase.todo

import cleanArch.application.repository.auth.UserRepository
import cleanArch.application.repository.todo.EditItemImpel
import cleanArch.contract.service.todo.EditItemService
import cleanArch.module.database.Holder

class EditItemUseCase(database: Holder) extends EditItemService {
  override def call(request: EditItemService.Request): Unit = {
    val userRep = UserRepository(database)
    val itemRep = EditItemImpel(database)
    val session = userRep.getSession(request.username)
    session match {
      case None => throw new Exception(s"User is Not Signed Up!")
      case Some(session) =>
        if (session.isLogin) {
          itemRep.editItemInRepo(request.id, request.field, request.text)
        } else {
          throw new Exception(s"User is Not Signed In")
        }
    }
  }
}

object EditItemUseCase {
  def apply(database: Holder): EditItemUseCase = new EditItemUseCase(database: Holder)
}
