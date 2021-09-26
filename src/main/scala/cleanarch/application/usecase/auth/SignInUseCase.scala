package cleanarch.application.usecase.auth

import cleanarch.contract.callback.auth._
import cleanarch.contract.service.auth._
import cleanarch.domain.auth.Session

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class SignInUseCase(userCallback: UserCallback, sessionCallback: SessionCallback) extends SignInService {

  override def call(request: SignInService.Request)(implicit ec: ExecutionContext): Future[Session] = for {
    userOption <- userCallback getUserByUsername request.username
    user <- userOption match {
      case None => Future failed new Exception(s"User Not found")
      case Some(user) =>
        if (user.password == request.password) {
          Future successful user
        } else {
          Future failed new Exception(s"Incorrect Password")
        }
    }
    sessionOption <- sessionCallback get user.id
    session <- sessionOption match {
      case None => Future failed new Exception(s"No Session Was Found for ${user.username}")
      case Some(session) =>
        if (session.isLogin) {
          Future failed new Exception(s"${user.username} Is Already Signed In")
        } else {
          Future successful session
        }
    }
    newSession = session.updateState(state = true)
    _ <- sessionCallback update newSession
  } yield newSession

}
