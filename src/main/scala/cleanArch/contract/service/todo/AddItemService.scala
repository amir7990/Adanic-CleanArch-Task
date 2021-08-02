package cleanArch.contract.service.todo

import cleanArch.contract.service._

abstract class AddItemService extends Service[AddItemService.Request, Unit]

object AddItemService {
  case class Request(text: String, state: Boolean)
}