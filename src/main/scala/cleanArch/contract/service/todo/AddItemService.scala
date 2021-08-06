package cleanArch.contract.service.todo

import cleanArch.contract.service._

abstract class AddItemService extends Service[AddItemService.Request, Unit]

object AddItemService {
  case class Request(username: String, text: String, state: Boolean)
}
