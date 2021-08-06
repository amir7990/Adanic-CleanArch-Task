package cleanArch.contract.service.auth

import cleanArch.contract.service.Service

import scala.util.Try

abstract class SignOutService extends Service[SignOutService.Request, Try[Unit]]

object SignOutService {
  case class Request(username: String)
}
