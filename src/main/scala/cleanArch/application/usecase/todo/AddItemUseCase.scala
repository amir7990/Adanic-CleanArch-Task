package cleanArch.application.usecase.todo

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.contract.service.todo.AddItemService

class AddItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends AddItemService {

  override def call(request: AddItemService.Request): Unit = {

    val userId = userCallback.getUserId(request.username)
    val user = userCallback.getUserById(userId).get
    val databaseId = itemCallback.getItemNumbers + 1
    val userItemId = user.idMap.size + 1
    val newUser = user.updateIdMap(user.idMap + (userItemId -> databaseId))
    userCallback.updateUser(userId, newUser)
    itemCallback.addItemCallback(request.text, request.state)
  }
}
