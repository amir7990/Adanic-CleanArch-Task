package cleanArch.contract.callback.auth

import cleanArch.domain.auth.Session
import cleanArch.module.database.Holder

abstract class UserCallback {

  val db: Holder
  def signInCallback(username: String, password: String): Unit
  def getSession(username: String): Option[Session]
  def signOutCallback(username: String): Unit
  def signUpCallback(username: String, password: String): Unit

}
