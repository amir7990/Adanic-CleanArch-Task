package cleanArch.application.repository.todo

import cleanArch.contract.callback.todo.EditItemRepo
import cleanArch.module.database.Holder

class EditItemImpel(database: Holder) extends EditItemRepo {

  override val db: Holder = database

  override def editItemInRepo(id: Int, field: String, text: String): Unit = {
    db.editItem(id, field, text)
  }
}

object EditItemImpel{
  def apply(database: Holder): EditItemImpel = new EditItemImpel(database: Holder)
}