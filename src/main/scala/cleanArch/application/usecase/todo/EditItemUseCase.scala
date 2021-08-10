package cleanArch.application.usecase.todo

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.contract.service.todo.EditItemService
import cleanArch.domain.todo.Item

import scala.concurrent.{ExecutionContext, Future}

class EditItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends EditItemService {

  override def call(request: EditItemService.Request)(implicit ec: ExecutionContext): Future[Item] = {
    for {
      userOption <- userCallback.getUserByUsername(request.username)
      user <- userOption match {
        case None => Future failed new NoSuchElementException(s"User Not found")
        case Some(user) => Future successful user
      }
      itemIdOption = user.getItemId(request.id)
      itemId <- itemIdOption match {
        case None => Future failed new NoSuchElementException(s"Item Not Found")
        case Some(id) => Future successful id
      }
      _ <- itemCallback.editItemCallback(itemId, request.field, request.text)
      item <- itemCallback.getItemCallback(itemId)
    } yield item
  }
}
