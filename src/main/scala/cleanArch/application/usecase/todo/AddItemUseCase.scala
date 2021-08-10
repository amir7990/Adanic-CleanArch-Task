package cleanArch.application.usecase.todo

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.contract.service.todo.AddItemService

import scala.concurrent.{ExecutionContext, Future}

class AddItemUseCase(itemCallback: ItemCallback, userCallback: UserCallback) extends AddItemService {

  override def call(request: AddItemService.Request)(implicit ec: ExecutionContext): Future[Unit] = {
    for {
      userOption <- userCallback.getUserByUsername(request.username)
      user <- userOption match {
        case None => Future failed new NoSuchElementException(s"User Not Found")
        case Some(user) => Future successful user
      }
      databaseId = itemCallback.getItemNumbers + 1
      userItemId = user.idMap.size + 1
      newUser = user.updateIdMap(user.idMap + (userItemId -> databaseId))
      _ <- userCallback.updateUser(user.id, newUser)
      _ <- itemCallback.addItemCallback(request.text, request.state)
    } yield Future.unit
  }
}
