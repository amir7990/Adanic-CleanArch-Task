package cleanArch.contract.service.todo

import cleanArch.contract.service.Service
import cleanArch.domain.todo.Item

abstract class GetItemService extends Service[GetItemService.Request, Item]

object GetItemService {
  case class Request(username: String, id: Int)
}
