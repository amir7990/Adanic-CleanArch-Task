package cleanArch.repository_package.repoImpel

import cleanArch.repository_package.repository._
import cleanArch.database.Holder
import cleanArch.entity.Items

class AddItemImpel(_db: Holder) extends AddItemRepo {

  override val db: Holder = _db

  override def addItemInRepo(id: Int, text: String, state: Boolean): Unit = {
    val item = Items(id, text, state)
    db.addItem(item)
  }
}

object AddItemImpel{
  def apply(_db: Holder): AddItemImpel = new AddItemImpel(_db)
}