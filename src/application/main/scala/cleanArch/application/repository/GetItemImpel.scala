package cleanArch.application.repository

import cleanArch.contract.callback.GetItemRepo
import cleanArch.domain.entity.Items
import cleanArch.module.Holder

class GetItemImpel(database: Holder) extends GetItemRepo{

  override val db: Holder = database

  override def getItemInRepo(id: Int): Items = {
    db.getItem(id)
  }
}
 object GetItemImpel{
   def apply(database: Holder): GetItemImpel = new GetItemImpel(database: Holder)
 }
