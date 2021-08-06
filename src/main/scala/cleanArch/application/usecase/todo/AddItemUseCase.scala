package cleanArch.application.usecase.todo

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.contract.service.todo.AddItemService

import scala.util.{Failure, Success, Try}

class AddItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends AddItemService {

   override def call(request: AddItemService.Request): Try[Unit] = {

    val userId = userCallback.getUserId(request.username)
    val user = userId match {
      case Success(id) => userCallback.getUserById(id).get
      case Failure(_) => throw new NoSuchElementException(s"User Not Found")
    }
    val databaseId = itemCallback.getItemNumbers + 1
    val userItemId = user.idMap.size + 1
    val newUser = user.updateIdMap(user.idMap + (userItemId -> databaseId))
    userCallback.updateUser(userId.get, newUser)
    itemCallback.addItemCallback(request.text, request.state)
  }
}
