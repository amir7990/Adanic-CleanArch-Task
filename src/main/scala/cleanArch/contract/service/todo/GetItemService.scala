package cleanArch.contract.service.todo

import cleanArch.contract.service.Service
import cleanArch.domain.todo.Item

abstract class GetItemService extends Service[GetItemService.Request, Option[Item]]

object GetItemService {
  case class Request(id: Int)
}