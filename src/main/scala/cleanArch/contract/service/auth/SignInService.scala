package cleanArch.contract.service.auth

import cleanArch.contract.service.Service

abstract class SignInService extends Service[SignInService.Request, Unit]

object SignInService {
  case class Request(username: String, password: String)
}
