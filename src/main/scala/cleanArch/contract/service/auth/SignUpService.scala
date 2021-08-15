package cleanArch.contract.service.auth

import cleanArch.contract.service.Service
import cleanArch.domain.auth.User

abstract class SignUpService extends Service[SignUpService.Request, User]

object SignUpService {
  case class Request(username: String, password: String)
}
