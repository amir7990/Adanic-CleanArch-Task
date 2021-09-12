package cleanarch.contract.service.auth

import cleanarch.contract.service.Service
import cleanarch.domain.auth.Session

abstract class SignInService extends Service[SignInService.Request, Session]

object SignInService {
  case class Request(username: String, password: String)
}
