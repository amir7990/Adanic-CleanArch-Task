package cleanarch.contract.service.auth

import cleanarch.contract.service.Service
import cleanarch.domain.auth.User

abstract class SignUpService extends Service[SignUpService.Request, User]

object SignUpService {
  case class Request(username: String, password: String)
}
