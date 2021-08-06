package cleanArch.application.usecase.todo

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.contract.service.todo.GetItemService
import cleanArch.domain.todo.Item

import scala.util.{Failure, Success, Try}

class GetItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends GetItemService {
  override def call(request: GetItemService.Request): Try[Item] = {
    val userId = userCallback.getUserId(request.username)
    val user = userId match {
      case Success(id) => userCallback.getUserById(id).get
      case Failure(_) => throw new NoSuchElementException(s"User Not Found")
    }
    val itemId = user.getItemId(request.id)
    itemCallback.getItemCallback(itemId)
  }
}
