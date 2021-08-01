package cleanArch.contract.callback

import cleanArch.domain.entity.Items
import cleanArch.module.Holder

abstract class GetItemRepo {

  val db: Holder

  def getItemInRepo(id: Int): Items

}

object GetItemRepo
