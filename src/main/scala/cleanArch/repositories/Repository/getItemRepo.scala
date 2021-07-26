package cleanArch.repositories.Repository
import cleanArch.DB._

abstract class getItemRepo {
  val db: Holder
  def getItemInRepo(id: Int): Items
}

object getItemRepo
