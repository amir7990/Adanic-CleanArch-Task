package cleanArch.application.usecase.todo

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.contract.service.todo.EditItemService
import cleanArch.domain.todo.Item

import scala.util.{Failure, Success, Try}

class EditItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends EditItemService {
  override def call(request: EditItemService.Request): Try[Item] = {
    val userId = userCallback.getUserId(request.username)
    val user = userId match {
      case Success(id) => userCallback.getUserById(id).get
      case Failure(_) => throw new NoSuchElementException(s"User Not Found")
    }
    val itemId = user.getItemId(request.id)
    itemCallback.editItemCallback(itemId, request.field, request.text)
    itemCallback.getItemCallback(itemId)
  }
}
