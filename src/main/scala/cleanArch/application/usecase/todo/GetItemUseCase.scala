package cleanArch.application.usecase.todo

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.contract.service.todo.GetItemService
import cleanArch.domain.todo.Item

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
      case Some(map) => map.get(request.id) match {
        case None => Future failed new Exception(s"Item Not Found")
        case Some(item) => Future successful item
      }
      case None => Future failed new Exception(s"User Not Found")
    }
  } yield item

}
