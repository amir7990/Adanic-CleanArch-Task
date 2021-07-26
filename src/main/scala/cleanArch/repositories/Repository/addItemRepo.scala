package cleanArch.repositories.Repository
import cleanArch.DB._
abstract class addItemRepo {
  val db: Holder
  def addItemInRepo(id: Int, text: String, state: Boolean): Unit
}

object addItemRepo