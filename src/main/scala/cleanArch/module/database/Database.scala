package cleanArch.module.database

import cleanArch.domain.auth.{Session, User}
import cleanArch.domain.todo.Item

import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

trait Database[T] {

  implicit val ec: ExecutionContext = Database.ec

  protected var data: Map[Int, T] = Map.empty

  def createId(idMap: Map[Int, _]): Int = {
    idMap.toList match {
      case _ :: _ => idMap.last._1 + 1
      case Nil => 1
    }
  }

  def addElement(t: T): Future[Unit] = {
    val id = createId(data)
    Future {
      data = data + (id -> t)
    }
  }

  def updateElement(id: Int, t: T): Future[Unit] = {
    Future {
      data = data + (id -> t)
    }
  }

  def getElement(id: Int): Future[Option[T]] = {
    Future {
      data.get(id)
    }
  }

  def getNumberOfElements: Int = data.size
}

object Database {

  private val ec: ExecutionContextExecutor = ExecutionContext fromExecutor Executors.newCachedThreadPool()

  object SessionDatabase extends Database[Session]

  object ItemDatabase extends Database[Item]

  object UserDatabase extends Database[User]

}
