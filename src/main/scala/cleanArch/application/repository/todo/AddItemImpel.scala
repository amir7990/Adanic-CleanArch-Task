package cleanArch.application.repository.todo

import cleanArch.contract.callback.todo.AddItemRepo
import cleanArch.domain.todo.Item
import cleanArch.module.database.Holder

class AddItemImpel(database: Holder) extends AddItemRepo {

  override val db: Holder = database

  override def addItemInRepo(text: String, state: Boolean): Unit = {
    val item = Item(text, state)
    db.addItem(1, item)
  }
}

object AddItemImpel {
  def apply(database: Holder): AddItemImpel = new AddItemImpel(database: Holder)
}