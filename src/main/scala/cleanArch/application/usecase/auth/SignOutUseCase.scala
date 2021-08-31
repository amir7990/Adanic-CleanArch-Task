package cleanArch.application.usecase.auth

import cleanArch.contract.callback.auth.{SessionCallback, UserCallback}
import cleanArch.contract.service.auth._
import cleanArch.domain.auth.Session

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
      case Some(session) => Future successful session
      case None => Future failed new Exception(s"No Session Was Found for ${user.username}")
    }
    _ <- if (!session.isLogin) {
      Future failed new Exception(s"${user.username} Is Already Signed Out")
    } else {
      sessionCallback update session.updateState(state = false)
    }
    newSessionOption <- sessionCallback get user.id
  } yield newSessionOption.get

}
