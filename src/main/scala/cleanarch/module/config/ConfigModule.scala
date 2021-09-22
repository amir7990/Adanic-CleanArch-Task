package cleanarch.module.config

import cleanarch.application.repository.database.repository.auth._
import cleanarch.application.repository.database.repository.todo.ItemRepository
import cleanarch.application.usecase.auth._
import cleanarch.contract.callback.auth._
import cleanarch.contract.callback.todo._
import cleanarch.contract.service.auth._
import cleanarch.contract.service.todo._
import cleanarch.application.usecase.todo._

sealed abstract class ConfigModule {

  val userCallback: UserCallback
  val itemCallback: ItemCallback
  val sessionCallback: SessionCallback
  val addItemService: AddItemService
  val editItemService: EditItemService
  val getItemService: GetItemService
  val signInService: SignInService
  val signUpService: SignUpService
  val signOutService: SignOutService

}


object ConfigModule {

  class ConfigModuleOne extends ConfigModule {

    override val userCallback: UserCallback = UserRepository
    override val sessionCallback: SessionCallback = SessionRepository
    override val itemCallback: ItemCallback = ItemRepository
    override val addItemService: AddItemService = new AddItemUseCase(itemCallback, userCallback)
    override val editItemService: EditItemService = new EditItemUseCase(itemCallback, userCallback)
    override val getItemService: GetItemService = new GetItemUseCase(itemCallback, userCallback)
    override val signInService: SignInService = new SignInUseCase(userCallback, sessionCallback)
    override val signUpService: SignUpService = new SignUpUseCase(userCallback,sessionCallback)
    override val signOutService: SignOutService = new SignOutUseCase(userCallback, sessionCallback)

  }

  object ConfigModuleOne extends ConfigModuleOne

}
