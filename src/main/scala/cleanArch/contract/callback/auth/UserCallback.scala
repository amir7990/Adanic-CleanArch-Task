package cleanArch.contract.callback.auth

import cleanArch.domain.auth.{Session, User}
import cleanArch.domain.todo.Item
import cleanArch.module.database.{Database}

import scala.util.Try

abstract class UserCallback {

  protected val itemDatabase: Database[Item]

  protected val userDatabase: Database[User]

  def signInCallback(username: String, password: String): Unit

  def getUserId(username: String): Int

  def getUserById(id: Int): Option[User]

  def signOutCallback(username: String): Unit

  def signUpCallback(username: String, password: String): Unit

  def updateUser(id: Int, user: User): Unit

}
