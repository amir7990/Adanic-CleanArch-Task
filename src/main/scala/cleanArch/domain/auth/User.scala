package cleanArch.domain.auth

case class User(username: String, password: String, idMap: Map[Int, Int]) {

  def getItemId(userItemId: Int): Int = {
    idMap(userItemId)
  }

  def updateIdMap(newIdMap: Map[Int, Int]): User = {
    copy(idMap = newIdMap)
  }

}
