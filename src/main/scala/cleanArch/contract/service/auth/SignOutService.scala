package cleanArch.contract.service.auth

import cleanArch.contract.service.Service
import cleanArch.domain.auth.Session

abstract class SignOutService extends Service[SignOutService.Request, Session]

object SignOutService {
  case class Request(username: String)
}
