package cleanarch.application.usecase.auth

import cleanarch.contract.callback.auth.{SessionCallback, UserCallback}
import cleanarch.contract.service.auth._
import cleanarch.domain.auth.User

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class SignUpUseCase(userCallback: UserCallback, sessionCallback: SessionCallback) extends SignUpService {

  override def call(request: SignUpService.Request)(implicit ec: ExecutionContext): Future[User] = for {
    userOption <- userCallback getUserByUsername request.username
    user <- userOption match {
      case None => userCallback add(request.username, request.password)
      case Some(_) => Future failed new Exception(s"Choose Another Username. ${request.username} Already Exists!")
    }
    sessionId = user.id
    _ <- sessionCallback add sessionId
  } yield user

}
