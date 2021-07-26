package cleanArch

abstract class Service {
  def getHelloWorld(id: Int): String
}

object Service