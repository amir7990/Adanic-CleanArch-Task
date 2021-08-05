package cleanArch.domain.auth

case class User(username: String, password: String, loginState: Boolean, idMap: Map[Int, Int]) {

  def getItemId(userItemId: Int): Int = {
    idMap(userItemId)
  }

  def updateIdMap(newIdMap: Map[Int, Int]): User = {
    copy(idMap = newIdMap)
  }

  def updateState(state: Boolean): User = {
    copy(loginState = state)
  }

}
