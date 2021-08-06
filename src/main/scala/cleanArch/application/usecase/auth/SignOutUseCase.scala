package cleanArch.application.usecase.auth

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.service.auth._

import scala.util.Try

class SignOutUseCase(userCallback: UserCallback) extends SignOutService {

  override def call(request: SignOutService.Request): Try[Unit] = {
    userCallback.signOutCallback(request.username)
  }

}
