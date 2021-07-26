package cleanArch.useCases

import cleanArch._

class UseCase extends Service {
  override def getHelloWorld(id: Int): String = {
    RepoImpel.searchHelloWorld(id)
  }
}

object UseCase extends UseCase
