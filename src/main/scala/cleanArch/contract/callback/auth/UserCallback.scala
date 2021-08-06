package cleanArch.contract.callback.auth

import cleanArch.domain.auth.{Session, User}
import cleanArch.domain.todo.Item
import cleanArch.module.database.{Database}

import scala.util.Try

abstract class UserCallback {

  protected val sessionDatabase: Database[Session]

  protected val itemDatabase: Database[Item]

  protected val userDatabase: Database[User]

  def signInCallback(username: String, password: String): Try[Unit]

  def getUserId(username: String): Try[Int]

  def getUserById(id: Int): Option[User]

  def signOutCallback(username: String): Try[Unit]

  def signUpCallback(username: String, password: String): Try[Unit]

  def updateUser(id: Int, user: User): Unit

}
