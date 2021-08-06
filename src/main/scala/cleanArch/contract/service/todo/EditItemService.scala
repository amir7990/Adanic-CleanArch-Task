package cleanArch.contract.service.todo

import cleanArch.contract.service.Service
import cleanArch.domain.todo.Item
import scala.util.Try

abstract class EditItemService extends Service[EditItemService.Request, Try[Item]]

object EditItemService {
  case class Request(username: String, id: Int, field: String, text: String)
}
