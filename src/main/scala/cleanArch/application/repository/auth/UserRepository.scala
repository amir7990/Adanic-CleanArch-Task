package cleanArch.application.repository.auth

import cleanArch.contract.callback.auth.UserCallback
import cleanArch.domain.auth.Session
import cleanArch.module.database.Holder

class UserRepository(database: Holder) extends UserCallback {

  override val db: Holder = database

  override def getSession(username: String): Option[Session] = {
    db.findSession(username)
  }

  override def signInCallback(username: String, password: String): Unit = {
    db.signIn(username, password)
  }

  override def signUpCallback(username: String, password: String): Unit = {
    db.signUp(username, password)
  }

  override def signOutCallback(username: String): Unit = {
    db.signOut(username)
  }

}

object UserRepository {
  def apply(database: Holder): UserRepository = new UserRepository(database)
}
