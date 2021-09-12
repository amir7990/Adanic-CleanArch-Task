package cleanarch.contract.callback.auth

import cleanarch.domain.auth.User
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

abstract class UserCallback {

  def getUserByUsername(username: String): Future[Option[User]]

  def getUserById(id: Long): Future[Option[User]]

  def add(username: String, password: String): Future[User]

  def update(id: Long, user: User): Future[User]

  def remove(id: Long): Future[Unit]

}
