package cleanArch

class Items {
  val items = Vector((1, "Salam Donya!"), (2, "Hello World!"), (3, "Welcome to Adanic!"))

  def getItems: Vector[(Int, String)] = items
}

object Items extends Items