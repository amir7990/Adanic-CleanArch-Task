package cleanarch.contract.service.auth

import cleanarch.contract.service.Service
import cleanarch.domain.auth.Session

abstract class SignOutService extends Service[SignOutService.Request, Session]

object SignOutService {
  case class Request(username: String)
}
