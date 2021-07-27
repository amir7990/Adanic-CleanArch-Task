package cleanArch.repository_package.repository
import cleanArch.database._
abstract class addItemRepo {
  val db: Holder
  def addItemInRepo(id: Int, text: String, state: Boolean): Unit
}

object addItemRepo