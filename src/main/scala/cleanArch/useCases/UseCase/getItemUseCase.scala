package cleanArch.useCases.UseCase

import cleanArch.useCases.Service._
import cleanArch.repositories.RepoImpel.getItemImpel

class getItemUseCase extends getItemService {
  override def getItem(id: Int): String = {
    getItemImpel.searchHelloWorld(id)
  }
}

object getItemUseCase extends getItemUseCase
