package cleanArch.useCases

abstract class Service {
  def getHelloWorld(id: Int): String
}

object Service