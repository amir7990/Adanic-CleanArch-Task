package cleanArch.application.usecase.auth

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.service.auth._
import cleanArch.domain.auth.Session

import scala.concurrent.{ExecutionContext, Future}

class SignUpUseCase(userCallback: UserCallback) extends SignUpService {

  override def call(request: SignUpService.Request)(implicit ec: ExecutionContext): Future[Session] = {
    userCallback signUpCallback(request.username, request.password)
  }

}
