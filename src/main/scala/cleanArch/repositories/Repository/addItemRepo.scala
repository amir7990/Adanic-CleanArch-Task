package cleanArch.repositories.Repository

abstract class addItemRepo {
  def addItemInRepo(id: Int, text: String): Unit
}
