package cleanArch.repository_package.repository

import cleanArch.database._
import cleanArch.entity.Items

abstract class GetItemRepo {

  val db: Holder

  def getItemInRepo(id: Int): Items

}

object GetItemRepo
