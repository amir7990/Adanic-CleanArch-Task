package cleanArch.application.usecase.auth

import cleanArch.application.repository.auth.UserRepository
import cleanArch.contract.service.auth._
import cleanArch.module.database.Holder

class SignInUseCase(database: Holder) extends SignInService {

  override def call(request: SignInService.Request): Unit = {
    val rep = UserRepository(database)
    rep.signInCallback(request.username, request.password)
  }

}

object SignInUseCase {
  def apply(database: Holder): SignInUseCase = new SignInUseCase(database)
}
