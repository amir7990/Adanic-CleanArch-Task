package cleanArch.application.usecase.auth

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.service.auth._

import scala.util.Try

class SignUpUseCase(userCallback: UserCallback) extends SignUpService {

  override def call(request: SignUpService.Request): Try[Unit] = {
    userCallback signUpCallback(request.username, request.password)
  }

}
