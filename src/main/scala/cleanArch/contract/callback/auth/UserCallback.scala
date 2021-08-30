package cleanArch.contract.callback.auth

import cleanArch.domain.auth.User
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

abstract class UserCallback {

  def getUserByUsername(username: String)(implicit ec: ExecutionContext): Future[Option[User]]

  def getUserById(id: Long)(implicit ec: ExecutionContext): Future[Option[User]]

  def add(username: String, password: String)(implicit ec: ExecutionContext): Future[Int]

  def update(id: Long, user: User)(implicit ec: ExecutionContext): Future[Int]

  def remove(id: Long)(implicit ec: ExecutionContext): Future[Unit]

}
