package cleanArch.domain.auth

case class User(id: Int, username: String, password: String, idMap: Map[Int, Int]) {

  def getItemId(userItemId: Int): Option[Int] = {
    idMap.get(userItemId)
  }

  def updateIdMap(newIdMap: Map[Int, Int]): User = {
    copy(idMap = newIdMap)
  }

}
