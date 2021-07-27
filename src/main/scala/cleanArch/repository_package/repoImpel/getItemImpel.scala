package cleanArch.repository_package.repoImpel

import cleanArch.database._
import cleanArch.repository_package.repository._

class getItemImpel(_db: Holder) extends getItemRepo {
  override val db: Holder = _db
  override def getItemInRepo(id: Int): Items = {
    db.getItem(id)
  }
}
 object getItemImpel{
   def apply(_db: Holder): getItemImpel = new getItemImpel(_db)
 }
