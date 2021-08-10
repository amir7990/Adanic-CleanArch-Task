package cleanArch.application.usecase.todo

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.contract.service.todo.GetItemService
import cleanArch.domain.todo.Item
import scala.concurrent.{ExecutionContext, Future}

class GetItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends GetItemService {
  override def call(request: GetItemService.Request)(implicit ec: ExecutionContext): Future[Item] = {
    for {
      userOption <- userCallback.getUserByUsername(request.username)
      user <- userOption match {
        case None => Future failed new NoSuchElementException(s"User Not Found")
        case Some(user) => Future successful user
      }
      itemIdOption = user.getItemId(request.id)
      itemId <- itemIdOption match {
        case None => Future failed new NoSuchElementException(s"Item Not Found")
        case Some(id) => Future successful id
      }
      item <- {
        itemCallback.getItemCallback(itemId)
      }
    } yield item
  }
}
