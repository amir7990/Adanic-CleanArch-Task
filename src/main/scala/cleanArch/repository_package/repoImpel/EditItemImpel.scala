package cleanArch.repository_package.repoImpel

import cleanArch.repository_package.repository._
import cleanArch.database._
class EditItemImpel(_db: Holder) extends EditItemRepo {
  override val db: Holder = _db

  override def editItemInRepo(id: Int, field: String, text: String): Unit = {
    db.editItem(id, field, text)
  }
}

object EditItemImpel{
  def apply(_db: Holder): EditItemImpel = new EditItemImpel(_db)
}