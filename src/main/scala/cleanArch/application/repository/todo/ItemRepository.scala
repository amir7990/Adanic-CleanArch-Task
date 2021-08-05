package cleanArch.application.repository.todo

import cleanArch.contract.callback.todo.ItemCallback
import cleanArch.domain.auth.User
import cleanArch.domain.todo.Item
import cleanArch.module.database.Database
import scala.util.Try

class ItemRepository(itemDB: Database[Item], userDB: Database[User]) extends ItemCallback {

  override protected val itemDatabase: Database[Item] = itemDB

  override protected val userDatabase: Database[User] = userDB

  override def addItemCallback(text: String, state: Boolean): Try[Unit] = {
    val item = Item(text, state)
    Try(itemDatabase.addElement(item))
  }

  override def getItemCallback(id: Int): Try[Item] = {
    val item = itemDatabase.getElement(id)
    item match {
      case None => throw new NoSuchElementException(s"Item Not Found")
      case Some(item) => Try(item)
    }
  }

  override def editItemCallback(id: Int, field: String, text: String): Try[Unit] = {
    val item = itemDatabase.getElement(id).get
    val newItem = field match {
      case "message" => item.editMessage(text)
      case "done" =>
        val done = text == "true"
        item.editState(done)
      case _ => throw new NoSuchElementException(s"Invalid Field")
    }
    Try(itemDatabase.updateElement(id, newItem))
  }

  override def getItemNumbers: Int = itemDatabase.getNumberOfElements
}

object ItemRepository {
  def apply(itemDB: Database[Item], userDB: Database[User]): ItemRepository = new ItemRepository(itemDB, userDB)
}
