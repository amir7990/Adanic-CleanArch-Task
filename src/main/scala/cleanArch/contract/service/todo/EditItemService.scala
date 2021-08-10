package cleanArch.contract.service.todo

import cleanArch.contract.service.Service
import cleanArch.domain.todo.Item

abstract class EditItemService extends Service[EditItemService.Request, Item]

object EditItemService {
  case class Request(username: String, id: Int, field: String, text: String)
}
