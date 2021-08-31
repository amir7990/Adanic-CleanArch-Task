package cleanArch.application.usecase.auth

import cleanArch.contract.callback.auth.{SessionCallback, UserCallback}
import cleanArch.contract.service.auth._
import cleanArch.domain.auth.User

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class SignUpUseCase(userCallback: UserCallback, sessionCallback: SessionCallback) extends SignUpService {

  override def call(request: SignUpService.Request)(implicit ec: ExecutionContext): Future[User] = for {
    userOption <- userCallback getUserByUsername request.username
    _ <- userOption match {
      case None => userCallback add(request.username, request.password)
      case Some(_) => Future failed new Exception(s"Choose Another Username. ${request.username} Already Exists!")
    }
    newUserOption <- userCallback getUserByUsername request.username
    user = newUserOption.get
    sessionId = user.id
    _ <- sessionCallback add sessionId
  } yield user

}
