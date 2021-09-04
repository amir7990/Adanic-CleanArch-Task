package cleanArch.contract.service.todo

import cleanArch.contract.service._
import cleanArch.domain.todo.Item

abstract class AddItemService extends Service[AddItemService.Request, Map[Int, Item]]

object AddItemService {
  case class Request(username: String, text: String, state: Boolean)
}
