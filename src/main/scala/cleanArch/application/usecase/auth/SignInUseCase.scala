package cleanArch.application.usecase.auth

import cleanArch.contract.callback.auth.SessionCallback
import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.service.auth._
import cleanArch.domain.auth.Session

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class SignInUseCase(userCallback: UserCallback, sessionCallback: SessionCallback) extends SignInService {

  override def call(request: SignInService.Request)(implicit ec: ExecutionContext): Future[Session] = for {
    userOption <- userCallback getUserByUsername request.username
    user <- userOption match {
      case None => Future failed new Exception(s"User Not found")
      case Some(user) => Future successful user
    }
    _ = if (user.username != request.password) {
      Future failed new Exception(s"Incorrect Password For ${user.username}")
    }
    sessionOption <- sessionCallback get user.id
    session <- sessionOption match {
      case Some(session) => Future successful session
      case None => Future failed new Exception(s"No Session Was Found for ${user.username}")
    }
    newSession <- if (session.isLogin) {
      Future failed new Exception(s"${user.username} Is Already Signed In")
    } else {
      sessionCallback update session.updateState(state = true)
    }
  } yield newSession

}
