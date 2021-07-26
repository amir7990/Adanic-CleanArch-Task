package cleanArch.repositories.RepoImpel

import cleanArch.DB._
import cleanArch.repositories.Repository._

class getItemImpel(_db: Holder) extends getItemRepo {
  override val db: Holder = _db
  override def getItemInRepo(id: Int): Items = {
    db.getItem(id)
  }
}
 object getItemImpel{
   def apply(_db: Holder): getItemImpel = new getItemImpel(_db)
 }
