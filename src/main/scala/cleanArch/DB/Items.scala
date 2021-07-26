package cleanArch.DB

class Items(_id: Int, _message: String) {
  var message: String = _message
  var id: Int = _id
  var Done: Boolean = true

}

object Items{
  def apply(_id: Int, _message: String): Items = new Items(_id, _message)
}