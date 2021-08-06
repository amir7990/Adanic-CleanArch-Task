package cleanArch.contract.service.auth

import cleanArch.contract.service.Service

abstract class SignUpService extends Service[SignUpService.Request, Unit]

object SignUpService {
  case class Request(username: String, password: String)
}
