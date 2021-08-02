package cleanArch.application.repository.todo

import cleanArch.contract.callback.todo.AddItemRepo
import cleanArch.domain.todo.Item
import cleanArch.module.database.Holder

class AddItemImpel(database: Holder) extends AddItemRepo {

  override val db: Holder = database

  override def addItemInRepo(id: Int, text: String, state: Boolean): Unit = {
    val item = Item(id, text, state)
    db.addItem(item)
  }
}

object AddItemImpel {
  def apply(database: Holder): AddItemImpel = new AddItemImpel(database: Holder)
}