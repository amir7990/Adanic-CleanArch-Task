package cleanarch.contract.service.todo

import cleanarch.contract.service.Service
import cleanarch.domain.todo.Item

abstract class EditItemService extends Service[EditItemService.Request, Item]

object EditItemService {
  case class Request(username: String, id: Int, field: String, text: String)
}
