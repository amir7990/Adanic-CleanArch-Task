package cleanArch.application.repository.inmemory.repository.auth

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.domain.auth.User
import cleanArch.module.inmemory.InMemoryModule

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class UserRepository extends UserCallback with InMemoryModule[User] {

  override def getUserByUsername(username: String)(implicit ec: ExecutionContext): Future[Option[User]] = Future {
    data.values.find(_.username == username)
  }

  override def getUserById(id: Long)(implicit ec: ExecutionContext): Future[Option[User]] = Future {
    data.values.find(_.id == id)
  }

  override def add(username: String, password: String)(implicit ec: ExecutionContext): Future[Int] = {
    val id = lastId
    val user = User(id, username, password)
    addElement(user)
    Future { id.toInt }
  }

  override def update(id: Long, user: User)(implicit ec: ExecutionContext): Future[Int] = {
    updateElement(id, user)
    Future { id.toInt }
  }

  override def remove(id: Long)(implicit ec: ExecutionContext): Future[Unit] = {
    removeElement(id)
  }

}

object UserRepository extends UserRepository
