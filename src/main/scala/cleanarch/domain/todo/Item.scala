package cleanarch.domain.todo

case class Item(id: Long, userId: Long, message: String, done: Boolean) {

  override def toString: String = {
    s"Message: $message \t State: $done"
  }

  def editState(state: Boolean): Item = {
    copy(done = state)
  }

  def editMessage(text: String): Item = {
    copy(message = text)
  }

}
