package cleanArch.database

class Items(_id: Int, _message: String, state: Boolean) {
  var message: String = _message
  var id: Int = _id
  var Done: Boolean = state

}

object Items{
  def apply(_id: Int, _message: String, state: Boolean): Items = new Items(_id, _message, state)
}