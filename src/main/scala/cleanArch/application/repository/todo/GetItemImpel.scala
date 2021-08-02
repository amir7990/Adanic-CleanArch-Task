package cleanArch.application.repository.todo

import cleanArch.contract.callback.todo.GetItemRepo
import cleanArch.domain.todo.Item
import cleanArch.module.database.Holder

class GetItemImpel(database: Holder) extends GetItemRepo {

  override val db: Holder = database

  override def getItemInRepo(id: Int): Option[Item] = {
    db.getItem(id)
  }
}

object GetItemImpel {
  def apply(database: Holder): GetItemImpel = new GetItemImpel(database: Holder)
}
