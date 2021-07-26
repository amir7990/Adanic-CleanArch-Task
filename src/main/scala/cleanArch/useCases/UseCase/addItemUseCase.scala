package cleanArch.useCases.UseCase
import cleanArch.useCases.Service.addItemService

class addItemUseCase extends addItemService {
  override def addItem(id: Int, text: String): Unit = ???
}

object addItemUseCase extends addItemUseCase
