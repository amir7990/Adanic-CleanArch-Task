package cleanArch.contract.service.auth

import cleanArch.contract.service.Service
import cleanArch.domain.auth.Session

abstract class SignUpService extends Service[SignUpService.Request, Session]

object SignUpService {
  case class Request(username: String, password: String)
}
