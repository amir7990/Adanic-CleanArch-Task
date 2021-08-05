package cleanArch.application.usecase.auth

import cleanArch.application.repository.auth.UserRepository
import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.contract.service.auth._
import cleanArch.module.database.Holder

class SignUpUseCase(userCallback: UserCallback) extends SignUpService {

  override def call(request: SignUpService.Request): Unit = {
    userCallback signUpCallback(request.username, request.password)
  }

}
