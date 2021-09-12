package cleanarch.contract.service.todo

import cleanarch.contract.service.Service
import cleanarch.domain.todo.Item

abstract class GetItemService extends Service[GetItemService.Request, Item]

object GetItemService {
  case class Request(username: String, id: Int)
}
