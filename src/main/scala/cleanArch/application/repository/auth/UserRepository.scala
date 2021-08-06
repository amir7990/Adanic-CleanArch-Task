package cleanArch.application.repository.auth

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.domain.auth.{Session, User}
import cleanArch.domain.todo.Item
import cleanArch.module.database.Database

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

class UserRepository(itemDB: Database[Item], userDB: Database[User], sessionDB: Database[Session]) extends UserCallback {

  override protected val itemDatabase: Database[Item] = itemDB
  override protected val sessionDatabase: Database[Session] = sessionDB
  override protected val userDatabase: Database[User] = userDB

  override def getUserId(username: String): Int = {

    val userNumbers = userDatabase.getNumberOfElements

    @tailrec
    def getUserById(username: String, id: Int): Int = {
      val user = userDatabase.getElement(id)
      user match {
        case None => throw new NoSuchElementException(s"User Not Found")
        case Some(user) if user.username == username => id
        case Some(_) => getUserById(username, id - 1)
      }
    }

    getUserById(username, userNumbers)
  }

  override def getUserById(id: Int): Option[User] = {
    userDatabase.getElement(id)
  }

  override def signInCallback(username: String, password: String): Unit = {
    val userId = Try(getUserId(username))
    val user = userId match {
      case Success(id) => userDatabase.getElement(id).get
      case Failure(_) => throw new NoSuchElementException(s"User Not Found")
    }
    val session = sessionDatabase.getElement(userId.get).get
    if (user.password != password) {
      throw new NoSuchElementException(s"Incorrect Password For $username")
    }
    if (session.isLogin) {
      throw new NoSuchElementException(s"$username Is Already Signed In")
    }
    val newSession = session.updateState(state = true)
    sessionDatabase.updateElement(newSession.userId, newSession)
  }

  override def signUpCallback(username: String, password: String): Unit = {
    val userId = Try(getUserId(username))
    val user = userId match {
      case Success(_) => throw new NoSuchElementException(s"User Already exists")
      case Failure(_) =>
        User(username, password, Map.empty)
    }
    val session = Session(sessionDatabase.getNumberOfElements + 1, isLogin = true)
    Try(sessionDatabase.addElement(session))
    Try(userDatabase.addElement(user))
  }

  override def signOutCallback(username: String): Unit = {
    val userId = Try(getUserId(username))
    val user = userId match {
      case Success(id) => userDatabase.getElement(id).get
      case Failure(_) => throw new NoSuchElementException(s"User Not Found")
    }
    val session = sessionDatabase.getElement(userId.get).get
    if (!session.isLogin) {
      throw new NoSuchElementException(s"$username Is Already Signed Up")
    }
    val newSession = session.updateState(state = false)
    sessionDatabase.updateElement(newSession.userId, newSession)
  }

  override def updateUser(id: Int, user: User): Unit = {
    userDatabase.updateElement(id, user)
  }

}

object UserRepository {
  def apply(itemDB: Database[Item], userDB: Database[User], sessionDB: Database[Session]): UserRepository =
    new UserRepository(itemDB, userDB, sessionDB)
}
