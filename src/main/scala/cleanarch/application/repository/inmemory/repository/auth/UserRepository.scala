package cleanarch.application.repository.inmemory.repository.auth

import cleanarch.contract.callback.auth.UserCallback
import cleanarch.domain.auth.User
import cleanarch.module.inmemory.InMemoryModule

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

class UserRepository extends UserCallback with InMemoryModule[User] {

  override def getUserByUsername(username: String): Future[Option[User]] = Future {
    data.values.find(_.username == username)
  }

  override def getUserById(id: Long): Future[Option[User]] = Future {
    data.values.find(_.id == id)
  }

  override def add(username: String, password: String): Future[User] = {
    val id = lastId
    val user = User(id, username, password)
    addElement(user)
  }

  override def update(id: Long, user: User): Future[User] = {
    updateElement(id, user)
  }

  override def remove(id: Long): Future[Unit] = {
    removeElement(id)
  }

}

object UserRepository extends UserRepository
