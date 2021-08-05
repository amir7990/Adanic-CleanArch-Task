package cleanArch.application.usecase.todo

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.contract.service.todo.EditItemService

class EditItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends EditItemService {
  override def call(request: EditItemService.Request): Unit = {
    val userId = userCallback.getUserId(request.username)
    val user = userCallback.getUserById(userId).get
    val itemId = user.getItemId(request.id)
    itemCallback.editItemCallback(itemId, request.field, request.text)
    itemCallback.getItemCallback(itemId)
  }
}
