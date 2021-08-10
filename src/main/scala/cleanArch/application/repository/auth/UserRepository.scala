package cleanArch.application.repository.auth

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.domain.auth.{Session, User}
import cleanArch.domain.todo.Item
import cleanArch.module.database.Database

import scala.concurrent.{ExecutionContext, Future}


class UserRepository(itemDB: Database[Item], userDB: Database[User], sessionDB: Database[Session]) extends UserCallback with Database[User] {

  override protected val itemDatabase: Database[Item] = itemDB
  override protected val sessionDatabase: Database[Session] = sessionDB
  override protected val userDatabase: Database[User] = userDB

  override def getUserByUsername(username: String)(implicit ec: ExecutionContext): Future[Option[User]] = {
    val userNumbers = userDatabase.getNumberOfElements

    def getUserBy(username: String, id: Int): Future[Option[User]] = {
      for {
        userOption <- userDatabase.getElement(id)
        user <- userOption match {
          case None => Future successful None
          case Some(user) if user.username == username => Future successful Option(user)
          case Some(_) => getUserBy(username, id - 1)
        }
      } yield user
    }

    getUserBy(username, userNumbers)
  }

  override def getUserById(id: Int)(implicit ec: ExecutionContext): Future[Option[User]] = {
    userDatabase.getElement(id)
  }

  override def signInCallback(username: String, password: String)(implicit ec: ExecutionContext): Future[Unit] = {
    for {
      userId <- getUserByUsername(username)
      user <- userId match {
        case Some(user) => Future successful user
        case None => Future failed new NoSuchElementException(s"User Not Found")
      }
      session <- if (user.password != password) {
        Future failed new NoSuchElementException(s"Incorrect Password for $username")
      } else {
        sessionDatabase.getElement(user.id)
      }
      newSession <- if (session.get.isLogin) {
        Future failed new NoSuchElementException(s"$username Already Signed In")
      } else {
        val newSession = session.get.updateState(state = true)
        sessionDatabase.updateElement(newSession.userId, newSession)
      }
    } yield newSession
  }

  override def signUpCallback(username: String, password: String)(implicit ec: ExecutionContext): Future[Session] = {
    val userId = userDatabase.getNumberOfElements + 1
    for {
      userOption <- getUserByUsername(username)
      user <- userOption match {
        case Some(_) => Future failed new NoSuchElementException(s"$username Already Exists")
        case None => Future successful User(userId, username, password, Map.empty)
      }
      session = Session(user.id, isLogin = true)
      _ <- sessionDatabase.addElement(session)
      _ <- userDatabase.addElement(user)
    } yield session
  }

  override def signOutCallback(username: String)(implicit ec: ExecutionContext): Future[Unit] = {
    for {
      userOption <- getUserByUsername(username)
      session <- userOption match {
        case Some(user) => sessionDatabase.getElement(user.id)
        case None => Future failed new NoSuchElementException(s"User Not Found")
      }
      unit <- if (!session.get.isLogin) {
        Future failed new NoSuchElementException(s"$username Already Signed Out")
      } else {
        val newSession = session.get.updateState(state = false)
        sessionDatabase.updateElement(newSession.userId, newSession)
      }
    } yield unit
  }

  override def updateUser(id: Int, user: User)(implicit ec: ExecutionContext): Future[Unit] = {
    userDatabase.updateElement(id, user)
  }

}

object UserRepository {
  def apply(itemDB: Database[Item], userDB: Database[User], sessionDB: Database[Session]): UserRepository =
    new UserRepository(itemDB, userDB, sessionDB)
}
