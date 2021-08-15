package cleanArch.application.usecase.todo

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.contract.service.todo.AddItemService
import cleanArch.domain.todo.Item

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class AddItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends AddItemService {

  override def call(request: AddItemService.Request)(implicit ec: ExecutionContext): Future[Map[Int, Item]] = for {
    userOption <- userCallback getUserByUsername request.username
    user <- userOption match {
      case None => Future failed new NoSuchElementException(s"User Not Found")
      case Some(user) => Future successful user
    }
    item <- itemCallback addItemCallback(user.id, request.text, request.state)
  } yield item

}
