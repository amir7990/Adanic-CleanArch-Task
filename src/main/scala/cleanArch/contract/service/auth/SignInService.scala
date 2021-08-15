package cleanArch.contract.service.auth

import cleanArch.contract.service.Service
import cleanArch.domain.auth.Session

abstract class SignInService extends Service[SignInService.Request, Session]

object SignInService {
  case class Request(username: String, password: String)
}
