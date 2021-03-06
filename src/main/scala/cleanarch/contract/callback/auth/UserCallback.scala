package cleanarch.contract.callback.auth

import cleanarch.domain.auth.User

import scala.concurrent.Future

abstract class UserCallback {

  def getUserByUsername(username: String): Future[Option[User]]

  def getUserById(id: Long): Future[Option[User]]

  def add(username: String, password: String): Future[User]

  def update(user: User): Future[Unit]

  def remove(id: Long): Future[Unit]

}
