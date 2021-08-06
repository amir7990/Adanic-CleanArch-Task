package cleanArch.application.usecase.auth

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.service.auth._
import scala.util.Try

class SignInUseCase(userCallback: UserCallback) extends SignInService {

  override def call(request: SignInService.Request): Try[Unit] = {
    userCallback.signInCallback(request.username, request.password)
  }

}
