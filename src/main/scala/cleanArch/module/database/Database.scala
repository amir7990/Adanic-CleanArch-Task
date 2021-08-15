package cleanArch.module.database

import cleanArch.domain.auth.Session
import cleanArch.domain.auth.User
import cleanArch.domain.todo.Item

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.Future

trait Database[T] {

  implicit val ec: ExecutionContext = Database.ec

  protected var data: Map[Long, T] = Map.empty

  val id : AtomicLong = new AtomicLong(1)
  var lastId : Long = 1

  def createId(): Long = {
    id.getAndIncrement()
  }

  def addElement(t: T): Future[T] = Future {
    val id = createId()
    data synchronized  {
      data = data + (id -> t)
      lastId = id + 1
    }
    t
  }

  def updateElement(id: Long, t: T): Future[T] = Future {
    data synchronized  {
      data = data + (id -> t)
    }
    t
  }

  def getElement(id: Long): Future[Option[T]] = Future {
    data synchronized {
      data.get(id)
    }
  }

  def removeElement(id: Long): Future[Unit] = Future {
    data synchronized {
      data = data removed id
    }
  }

}

object Database {

  private val ec: ExecutionContextExecutor = ExecutionContext fromExecutor Executors.newCachedThreadPool()

  object SessionDatabase extends Database[Session]

  object ItemDatabase extends Database[Map[Int, Item]]

  object UserDatabase extends Database[User]

}
