package cleanArch.application.usecase.auth

import cleanArch.application.repository.auth.UserRepository
import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.contract.service.auth._
import cleanArch.module.database.Holder

class SignOutUseCase(userCallback: UserCallback) extends SignOutService {

  override def call(request: SignOutService.Request): Unit = {
    userCallback.signOutCallback(request.username)
  }

}
