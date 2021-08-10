package cleanArch.application.repository.todo

import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.domain.auth.User
import cleanArch.domain.todo.Item
import cleanArch.module.database.Database

import scala.concurrent.{ExecutionContext, Future}

class ItemRepository(itemDB: Database[Item], userDB: Database[User]) extends ItemCallback {

  override protected val itemDatabase: Database[Item] = itemDB

  override protected val userDatabase: Database[User] = userDB

  override def addItemCallback(text: String, state: Boolean)(implicit ec: ExecutionContext): Future[Unit] = {
    val item = Item(text, state)
    itemDatabase.addElement(item)
  }

  override def getItemCallback(id: Int)(implicit ec: ExecutionContext): Future[Item] = {
    for {
      itemOption <- itemDatabase.getElement(id)
      item <- itemOption match {
        case Some(item) => Future successful item
        case None => Future failed new NoSuchElementException(s"Item Not Found")
      }
    } yield item
  }

  override def editItemCallback(id: Int, field: String, text: String)(implicit ec: ExecutionContext): Future[Unit] = {
    for {
      itemOption <- itemDatabase.getElement(id)
      item <- itemOption match {
        case Some(item) => Future successful item
        case None => Future failed new NoSuchElementException(s"Item Not Found")
      }
      newItem <- field match {
        case "message" => Future successful item.editMessage(text)
        case "done" =>
          val done = text == "true"
          Future successful item.editState(done)
        case _ => Future failed new NoSuchElementException(s"Invalid Field")
      }
      unit <- itemDatabase.updateElement(id, newItem)
    } yield unit
  }


  override def getItemNumbers: Int = itemDatabase.getNumberOfElements
}

object ItemRepository {
  def apply(itemDB: Database[Item], userDB: Database[User]): ItemRepository = new ItemRepository(itemDB, userDB)
}
