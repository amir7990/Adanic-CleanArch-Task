package cleanArch.module.config

import cleanArch.application.repository.auth._
import cleanArch.application.usecase.auth._
import cleanArch.contract.callback.auth._
import cleanArch.contract.callback.todo._
import cleanArch.contract.service.auth._
import cleanArch.contract.service.todo._
import cleanArch.application.repository.todo._
import cleanArch.application.usecase.todo._

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

  class ConfigModuleOne$ extends ConfigModule {

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

  object ConfigModuleOne extends ConfigModuleOne$

}
