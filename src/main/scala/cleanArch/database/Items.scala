package cleanArch.database

class Items(idNum: Int, messageText: String, state: Boolean) {
  var message: String = messageText
  var id: Int = idNum
  var Done: Boolean = state

}

object Items{
  def apply(idNum: Int, messageText: String, state: Boolean): Items = new Items(idNum, messageText, state)
}