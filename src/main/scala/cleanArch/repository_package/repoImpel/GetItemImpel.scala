package cleanArch.repository_package.repoImpel

import cleanArch.database._
import cleanArch.entity.Items
import cleanArch.repository_package.repository._

class GetItemImpel(_db: Holder) extends GetItemRepo {

  override val db: Holder = _db

  override def getItemInRepo(id: Int): Items = {
    db.getItem(id)
  }
}
 object GetItemImpel{
   def apply(_db: Holder): GetItemImpel = new GetItemImpel(_db)
 }
