package cleanarch.application.usecase.todo

import cleanarch.contract.callback.auth.UserCallback
import cleanarch.contract.callback.todo.ItemCallback
import cleanarch.contract.service.todo.AddItemService
import cleanarch.domain.todo.Item

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class AddItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends AddItemService {

  override def call(request: AddItemService.Request)(implicit ec: ExecutionContext): Future[Item] = for {
    userOption <- userCallback getUserByUsername request.username
    user <- userOption match {
      case None => Future failed new NoSuchElementException(s"User Not Found")
      case Some(user) => Future successful user
    }
    item <- itemCallback add(user.id, request.text, request.state)
  } yield item

}
