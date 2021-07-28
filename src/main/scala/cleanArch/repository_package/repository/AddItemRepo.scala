package cleanArch.repository_package.repository

import cleanArch.database._

abstract class AddItemRepo {

  val db: Holder

  def addItemInRepo(id: Int, text: String, state: Boolean): Unit

}

object AddItemRepo