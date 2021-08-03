package cleanArch.application.usecase.auth

import cleanArch.application.repository.auth.UserRepository
import cleanArch.contract.service.auth._
import cleanArch.module.database.Holder

class SignOutUseCase(database: Holder) extends SignOutService {

  override def call(request: SignOutService.Request): Unit = {
    val rep = UserRepository(database)
    rep.signOutCallback(request.username)
  }

}

object SignOutUseCase {
  def apply(database: Holder): SignOutUseCase = new SignOutUseCase(database)
}
