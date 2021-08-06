package cleanArch.module.database

import cleanArch.domain.auth.{Session, User}
import cleanArch.domain.todo.Item
import scala.util.Try

trait Database[T] {

  protected var data: Map[Int, T] = Map.empty

  def createId(idMap: Map[Int, _]): Int = {
    idMap.toList match {
      case _ :: _ => idMap.last._1 + 1
      case Nil => 1
    }
  }

  def addElement(t: T): Unit = {
    val id = createId(data)
    synchronized {
      data = data + (id -> t)
    }
  }

  def updateElement(id: Int, t: T): Unit = {
    data = data + (id -> t)
  }

  def getElement(id: Int): Option[T] = {
    data.get(id)
  }

  def getNumberOfElements: Int = data.size

}

object Database {

  object SessionDatabase extends Database[Session]
  object ItemDatabase extends Database[Item]
  object UserDatabase extends Database[User]

}
