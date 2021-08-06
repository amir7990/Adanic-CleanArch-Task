package cleanArch.contract.service.auth

import cleanArch.contract.service.Service

import scala.util.Try

abstract class SignInService extends Service[SignInService.Request, Try[Unit]]

object SignInService {
  case class Request(username: String, password: String)
}
