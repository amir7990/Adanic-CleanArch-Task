package cleanArch.application.usecase.auth

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.service.auth._

import scala.concurrent.{ExecutionContext, Future}

class SignInUseCase(userCallback: UserCallback) extends SignInService {

  override def call(request: SignInService.Request)(implicit ec: ExecutionContext): Future[Unit] = {
    userCallback.signInCallback(request.username, request.password)
  }
}
