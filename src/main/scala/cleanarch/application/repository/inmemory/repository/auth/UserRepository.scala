package cleanarch.application.repository.inmemory.repository.auth

import cleanarch.contract.callback.auth.UserCallback
import cleanarch.domain.auth.User
import cleanarch.module.inmemory.InMemoryModule

import scala.concurrent.Future

class UserRepository extends UserCallback with InMemoryModule[User] {

  override val name: String = "User"

  override def getUserByUsername(username: String): Future[Option[User]] = Future {
    data.find(_.username == username)
  }

  override def getUserById(id: Long): Future[Option[User]] = Future {
    data.find(_.id == id)
  }

  override def add(username: String, password: String): Future[User] = {
    val user = User(createId(), username, password)
    addElement(user)
  }

  override def update(user: User): Future[Unit] = {
    updateElement(user, u => u.id == user.id)
  }

  override def remove(id: Long): Future[Unit] = {
    removeElement(u => u.id == id)
  }

}

object UserRepository extends UserRepository
