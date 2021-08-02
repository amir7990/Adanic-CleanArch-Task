package cleanArch.contract.service.todo

import cleanArch.contract.service.Service

abstract class EditItemService extends Service[EditItemService.Request, Unit]

object EditItemService {
  case class Request(id: Int, field: String, text: String)
}