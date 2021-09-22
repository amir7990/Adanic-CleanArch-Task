package cleanarch.module.inmemory

import cleanarch.domain.auth.Session
import cleanarch.domain.auth.User
import cleanarch.domain.todo.Item

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.Future

trait InMemoryModule[T] {

  implicit val ec: ExecutionContext = InMemoryModule.ec

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

  def updateElement(id: Long, t: T): Future[Unit] = Future {
    data synchronized  {
      data = data + (id -> t)
    }
  }

  def getElement(id: Long): Future[Option[T]] = Future {
    data synchronized {
      data.get(id)
    }
  }

  def removeElement(id: Long): Future[Unit] = Future {
    data synchronized {
      data = data.-(id)
    }
  }

}

object InMemoryModule {

  private val ec: ExecutionContextExecutor = ExecutionContext fromExecutor Executors.newCachedThreadPool()

  object SessionInMemoryModule extends InMemoryModule[Session]

  object ItemInMemoryModule extends InMemoryModule[Map[Int, Item]]

  object UserInMemoryModule extends InMemoryModule[User]

}
