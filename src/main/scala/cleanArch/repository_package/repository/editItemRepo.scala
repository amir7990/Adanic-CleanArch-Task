package cleanArch.repository_package.repository

import cleanArch.database._
abstract class editItemRepo {
  val db: Holder
  def editItemInRepo(id: Int, field: String, text: String): Unit
}

object editItemRepo