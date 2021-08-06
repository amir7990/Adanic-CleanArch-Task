package cleanArch.module.config

import cleanArch.application.repository.auth.UserRepository
import cleanArch.application.usecase.auth._
import cleanArch.contract.callback.auth.UserCallback
import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.contract.service.auth._
import cleanArch.contract.service.todo._
import cleanArch.domain.auth.{Session, User}
import cleanArch.domain.todo.Item
import cleanArch.module.database.Database
import cleanArch.application.repository.todo._
import cleanArch.application.usecase.todo.{AddItemUseCase, EditItemUseCase, GetItemUseCase}

sealed abstract class Config {

  val sessionDatabase: Database[Session]
  val itemDatabase: Database[Item]
  val userDatabase: Database[User]
  val userCallback: UserCallback
  val itemCallback: ItemCallback
  val addItemService: AddItemService
  val editItemService: EditItemService
  val getItemService: GetItemService
  val signInService: SignInService
  val signUpService: SignUpService
  val signOutService: SignOutService

}


object Config {
  class ConfigOne extends Config {

    override val sessionDatabase: Database[Session] = Database.SessionDatabase
    override val itemDatabase: Database[Item] = Database.ItemDatabase
    override val userDatabase: Database[User] = Database.UserDatabase
    override val userCallback: UserCallback = UserRepository(itemDatabase, userDatabase, sessionDatabase)
    override val itemCallback: ItemCallback = ItemRepository(itemDatabase, userDatabase)
    override val addItemService: AddItemService = new AddItemUseCase(itemCallback, userCallback)
    override val editItemService: EditItemService = new EditItemUseCase(itemCallback, userCallback)
    override val getItemService: GetItemService = new GetItemUseCase(itemCallback, userCallback)
    override val signInService: SignInService = new SignInUseCase(userCallback)
    override val signUpService: SignUpService = new SignUpUseCase(userCallback)
    override val signOutService: SignOutService = new SignOutUseCase(userCallback)

  }

  object ConfigOne extends ConfigOne
}
