package cleanArch.contract.callback.todo

import cleanArch.domain.auth.User
import cleanArch.domain.todo.Item
import cleanArch.module.database.Database

import scala.concurrent.{ExecutionContext, Future}

abstract class ItemCallback {

  protected val itemDatabase: Database[Item]

  protected val userDatabase: Database[User]

  def addItemCallback(text: String, state: Boolean)(implicit ec: ExecutionContext): Future[Unit]

  def getItemCallback(id: Int)(implicit ec: ExecutionContext): Future[Item]

  def editItemCallback(id: Int, field: String, text: String)(implicit ec: ExecutionContext): Future[Unit]

  def getItemNumbers: Int

}
