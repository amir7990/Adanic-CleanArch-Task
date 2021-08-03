package cleanArch.module.config

import cleanArch.application.usecase.auth._
import cleanArch.contract.service.todo._
import cleanArch.domain.todo.Item
import cleanArch.module.database.Holder
import cleanArch.application.usecase.todo._
import cleanArch.contract.service.auth._

sealed abstract class Config {

  val db: Holder
  val addItemService: AddItemService
  val editItemService: EditItemService
  val getItemService: GetItemService
  val signInService : SignInService
  val signUpService : SignUpService
  val signOutService : SignOutService

  def addItem(username: String, message: String, state: Boolean): Unit = {
    addItemService.call(AddItemService.Request(username, message, state))
  }

  def getItem(username: String, id: Int): Option[Item] = {
    getItemService.call(GetItemService.Request(username, id))
  }

  def editItem(username: String, id: Int, field: String, text: String): Unit = {
    editItemService.call(EditItemService.Request(username, id, field, text))
  }

  def signIn(username: String, password: String): Unit = {
    signInService.call(SignInService.Request(username, password))
  }

  def signUp(username: String, password: String): Unit = {
    signUpService.call(SignUpService.Request(username, password))
  }

  def signOut(username: String): Unit = {
    signOutService.call(SignOutService.Request(username))
  }
}


object Config {
  class ConfigOne extends Config {

    override val db: Holder = Holder()
    override val addItemService: AddItemService = AddItemUseCase(db)
    override val editItemService: EditItemService = EditItemUseCase(db)
    override val getItemService: GetItemService = GetItemUseCase(db)
    override val signInService: SignInService = SignInUseCase(db)
    override val signUpService: SignUpService = SignUpUseCase(db)
    override val signOutService: SignOutService = SignOutUseCase(db)

  }

  object ConfigOne extends ConfigOne
}
