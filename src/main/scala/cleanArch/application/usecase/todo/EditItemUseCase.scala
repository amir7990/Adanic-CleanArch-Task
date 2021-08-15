package cleanArch.application.usecase.todo

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.contract.service.todo.EditItemService
import cleanArch.domain.todo.Item

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class EditItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends EditItemService {

  override def call(request: EditItemService.Request)(implicit ec: ExecutionContext): Future[Item] = for {
    userOption <- userCallback.getUserByUsername(request.username)
    user <- userOption match {
      case None => Future failed new NoSuchElementException(s"User Not found")
      case Some(user) => Future successful user
    }
    itemMapOption <- itemCallback getItemCallback user.id
    itemMap <- itemMapOption match {
      case None => Future failed new Exception(s"${request.username} Has No Items")
      case Some(map) => Future successful map
    }
    itemOption = itemMap.get(request.id)
    item <- itemOption match {
      case None => Future failed new NoSuchElementException(s"Item Not found")
      case Some(item) => Future successful item
    }
    newItem = request.field match {
      case "message" => item.editMessage(request.text)
      case "done" =>
        val done = request.text == "true"
        item.editState(done)
    }
    newItemMap = itemMap + (request.id -> newItem)
    _ <- itemCallback updateItemCallback(user.id, newItemMap)
  } yield newItem

}
