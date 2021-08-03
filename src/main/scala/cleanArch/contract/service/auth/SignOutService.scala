package cleanArch.contract.service.auth

import cleanArch.contract.service.Service

abstract class SignOutService extends Service[SignOutService.Request, Unit]

object SignOutService {
  case class Request(username: String)
}