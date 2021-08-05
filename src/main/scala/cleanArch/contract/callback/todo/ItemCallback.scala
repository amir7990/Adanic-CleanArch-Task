package cleanArch.contract.callback.todo

import cleanArch.domain.auth.User
import cleanArch.domain.todo.Item
import cleanArch.module.database.Database

import scala.util.Try

abstract class ItemCallback {

  protected val itemDatabase: Database[Item]

  protected val userDatabase: Database[User]

  def addItemCallback(text: String, state: Boolean) : Try[Unit]

  def getItemCallback(id: Int): Try[Item]

  def editItemCallback(id: Int, field: String, text: String): Try[Unit]

  def getItemNumbers : Int

}
