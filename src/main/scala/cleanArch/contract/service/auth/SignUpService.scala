package cleanArch.contract.service.auth

import cleanArch.contract.service.Service

import scala.util.Try

abstract class SignUpService extends Service[SignUpService.Request, Try[Unit]]

object SignUpService {
  case class Request(username: String, password: String)
}
