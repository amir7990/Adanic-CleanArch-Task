package cleanArch.contract.service.todo

import cleanArch.contract.service.Service
import cleanArch.domain.todo.Item

import scala.util.Try

abstract class GetItemService extends Service[GetItemService.Request, Try[Item]]

object GetItemService {
  case class Request(username: String, id: Int)
}