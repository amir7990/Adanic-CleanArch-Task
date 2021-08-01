package cleanArch.application.repository

import cleanArch.contract.callback.AddItemRepo
import cleanArch.domain.entity.Items
import cleanArch.module._

class AddItemImpel(database: Holder) extends AddItemRepo{

  override val db: Holder = database

  override def addItemInRepo(id: Int, text: String, state: Boolean): Unit = {
    val item = Items(id, text, state)
    db.addItem(item)
  }
}

object AddItemImpel{
  def apply(database: Holder): AddItemImpel = new AddItemImpel(database: Holder)
}