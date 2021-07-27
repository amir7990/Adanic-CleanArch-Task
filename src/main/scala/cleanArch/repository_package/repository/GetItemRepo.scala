package cleanArch.repository_package.repository
import cleanArch.database._

abstract class GetItemRepo {
  val db: Holder
  def getItemInRepo(id: Int): Items
}

object GetItemRepo
