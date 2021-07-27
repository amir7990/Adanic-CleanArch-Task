package cleanArch.repository_package.repository
import cleanArch.database._

abstract class getItemRepo {
  val db: Holder
  def getItemInRepo(id: Int): Items
}

object getItemRepo
