package cleanArch.application.usecase.todo

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.contract.service.todo.AddItemService
import cleanArch.domain.todo.Item

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class AddItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends AddItemService {

  override def call(request: AddItemService.Request)(implicit ec: ExecutionContext): Future[Item] = for {
    userOption <- userCallback getUserByUsername request.username
    user <- userOption match {
      case None => Future failed new NoSuchElementException(s"User Not Found")
      case Some(user) => Future successful user
    }
    itemId <- itemCallback addItemCallback(user.id, request.text)
    item <- itemCallback getItemCallback itemId
  } yield item.get

}
