package cleanArch.contract.callback.auth

import cleanArch.domain.auth.{Session, User}
import cleanArch.domain.todo.Item
import cleanArch.module.database.Database

import scala.concurrent.{ExecutionContext, Future}

abstract class UserCallback {

  protected val sessionDatabase: Database[Session]

  protected val itemDatabase: Database[Item]

  protected val userDatabase: Database[User]

  def signInCallback(username: String, password: String)(implicit ec: ExecutionContext): Future[Unit]

  def getUserByUsername(username: String)(implicit ec: ExecutionContext): Future[Option[User]]

  def getUserById(id: Int)(implicit ec: ExecutionContext): Future[Option[User]]

  def signOutCallback(username: String)(implicit ec: ExecutionContext): Future[Unit]

  def signUpCallback(username: String, password: String)(implicit ec: ExecutionContext): Future[Session]

  def updateUser(id: Int, user: User)(implicit ec: ExecutionContext): Future[Unit]

}
