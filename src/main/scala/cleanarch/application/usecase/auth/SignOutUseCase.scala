package cleanarch.application.usecase.auth

import cleanarch.contract.callback.auth._
import cleanarch.contract.service.auth._
import cleanarch.domain.auth.Session

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class SignOutUseCase(userCallback: UserCallback, sessionCallback: SessionCallback) extends SignOutService {

  override def call(request: SignOutService.Request)(implicit ec: ExecutionContext): Future[Session] = for {
    userOption <- userCallback getUserByUsername request.username
    user <- userOption match {
      case None => Future failed new Exception(s"User Not Found")
      case Some(user) => Future successful user
    }
    sessionOption <- sessionCallback get user.id
    session <- sessionOption match {
      case None => Future failed new Exception(s"No Session Was Found for ${user.username}")
      case Some(session) =>
        if (!session.isLogin) {
          Future failed new Exception(s"${user.username} Is Already Signed Out")
        } else {
          Future successful session
        }
    }
    newSession = session.updateState(state = false)
    _ <- sessionCallback update newSession
  } yield newSession

}
