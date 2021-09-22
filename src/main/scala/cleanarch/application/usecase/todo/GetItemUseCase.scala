package cleanarch.application.usecase.todo

import cleanarch.contract.callback.auth.UserCallback
import cleanarch.contract.callback.todo.ItemCallback
import cleanarch.contract.service.todo.GetItemService
import cleanarch.domain.todo.Item

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class GetItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends GetItemService {

  override def call(request: GetItemService.Request)(implicit ec: ExecutionContext): Future[Item] = for {
    userOption <- userCallback getUserByUsername request.username
    user <- userOption match {
      case None => Future failed new NoSuchElementException(s"User Not Found")
      case Some(user) => Future successful user
    }
    itemOption <- itemCallback getItemCallback user.id
    item <- itemOption match {
      case None => Future failed new Exception(s"Item Not Found")
      case Some(item) => Future successful item
    }
  } yield item

}
