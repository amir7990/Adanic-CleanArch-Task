package cleanarch.module.inmemory

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.Future

trait InMemoryModule[T] {

  implicit val ec: ExecutionContext = InMemoryModule.ec

  val name: String

  protected var data: Vector[T] = Vector.empty

  val id : AtomicLong = new AtomicLong(1)

  def createId(): Long = {
    id.getAndIncrement()
  }

  def addElement(t: T): Future[T] = Future {
    data synchronized  {
      data = data :+ t
    }
    t
  }

  def updateElement(t: T, predicate: T => Boolean): Future[Unit] = {
    val index = data indexWhere predicate
    if (index == -1) {
      Future failed new Exception(s"$name Not Found!")
    } else {
      Future {
        data synchronized {
          data = (data.take(index) :+ t) ++ data.drop(index + 1)
        }
      }
    }
  }

  def removeElement(predicate: T => Boolean): Future[Unit] = {
    val index = data indexWhere predicate
    if (index == -1) {
      Future failed new Exception(s"$name Not Found!")
    } else {
      Future {
        data synchronized {
          data = data.take(index) ++ data.drop(index + 1)
        }
      }
    }
  }

}

object InMemoryModule {

  private val ec: ExecutionContextExecutor = ExecutionContext fromExecutor Executors.newCachedThreadPool()

}
