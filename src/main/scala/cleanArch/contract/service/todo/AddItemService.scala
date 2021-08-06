package cleanArch.contract.service.todo

import cleanArch.contract.service._

import scala.util.Try

abstract class AddItemService extends Service[AddItemService.Request, Try[Unit]]

object AddItemService {
  case class Request(username: String, text: String, state: Boolean)
}
