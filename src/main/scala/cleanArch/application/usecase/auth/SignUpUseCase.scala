package cleanArch.application.usecase.auth

import cleanArch.application.repository.auth.UserRepository
import cleanArch.contract.service.auth._
import cleanArch.module.database.Holder

class SignUpUseCase(database: Holder) extends SignUpService {

  override def call(request: SignUpService.Request): Unit = {
    val rep = UserRepository(database)
    rep.signUpCallback(request.username, request.password)
  }

}

object SignUpUseCase {
  def apply(database: Holder): SignUpUseCase = new SignUpUseCase(database)
}