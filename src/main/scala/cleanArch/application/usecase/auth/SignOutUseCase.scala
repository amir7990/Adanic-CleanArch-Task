package cleanArch.application.usecase.auth

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.service.auth._

import scala.concurrent.{ExecutionContext, Future}

class SignOutUseCase(userCallback: UserCallback) extends SignOutService {

  override def call(request: SignOutService.Request)(implicit ec: ExecutionContext): Future[Unit] = {
    userCallback.signOutCallback(request.username)
  }
}
