package cleanArch.repository_package.repoImpel

import cleanArch.repository_package.repository._
import cleanArch.database._
class editItemImpel(_db: Holder) extends editItemRepo {
  override val db: Holder = _db

  override def editItemInRepo(id: Int, field: String, text: String): Unit = {
    db.editItem(id, field, text)
  }
}

object editItemImpel{
  def apply(_db: Holder): editItemImpel = new editItemImpel(_db)
}