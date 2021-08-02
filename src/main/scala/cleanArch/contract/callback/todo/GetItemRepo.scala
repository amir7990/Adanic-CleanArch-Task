package cleanArch.contract.callback.todo

import cleanArch.domain.todo.Item
import cleanArch.module.database.Holder

abstract class GetItemRepo {

  val db: Holder

  def getItemInRepo(id: Int): Option[Item]

}
