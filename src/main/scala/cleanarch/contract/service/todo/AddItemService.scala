package cleanarch.contract.service.todo

import cleanarch.contract.service._
import cleanarch.domain.todo.Item

abstract class AddItemService extends Service[AddItemService.Request, Map[Int, Item]]

object AddItemService {
  case class Request(username: String, text: String, state: Boolean)
}
