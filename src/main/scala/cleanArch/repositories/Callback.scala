package cleanArch.repositories

abstract class Callback {
  def searchHelloWorld(id: Int): String
}

object Callback
