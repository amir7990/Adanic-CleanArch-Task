package cleanArch

import cleanArch.contract.service.auth._
import cleanArch.contract.service.todo.{AddItemService, EditItemService, GetItemService}
import cleanArch.module.config.Config

import java.util.concurrent.Executors
import scala.annotation.tailrec
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutor, Future}
import scala.language.postfixOps

class cleanArchTest extends munit.FunSuite {
  implicit val ec: ExecutionContextExecutor = ExecutionContext fromExecutor Executors.newCachedThreadPool()

  import Config.ConfigOne._

  def toDo(v: Vector[Future[Any]]): Any = {
    @tailrec
    def iterator(jobs: Vector[Future[Any]]): Any = {
      if (jobs.size == 1) {
        println(jobs)
        Await.result(jobs.head, Duration("1 seconds"))
      } else {
        println(jobs)
        Await.result(jobs.head, Duration("1 seconds"))
        iterator(jobs.tail)
      }
    }

    iterator(v)
  }

  test("Main Test") {
    // init
    val firstItem = "Hello World!"
    val secondItem = "SALAM DONYA!"
    val thirdItem = "Welcome To Adanic Corp."
    val editMessage = "Message Changed"
    val firstUser = "Amir"
    val firsPass = "7990"

    lazy val job1 = signUpService call SignUpService.Request(firstUser, firsPass)
    lazy val job2 = addItemService call AddItemService.Request(firstUser, firstItem, state = true)
    lazy val job3 = addItemService call AddItemService.Request(firstUser, secondItem, state = true)
    lazy val job4 = addItemService call AddItemService.Request(firstUser, thirdItem, state = true)
    lazy val job5 = getItemService call GetItemService.Request(firstUser, 3)
    lazy val job6 = editItemService call EditItemService.Request(firstUser, 3, "message", editMessage)
    lazy val job7 = signInService call SignInService.Request(firstUser, "firsPass")
    Await.result(job1, Duration("1 seconds"))
    Await.result(job2, Duration("1 seconds"))
    Await.result(job3, Duration("1 seconds"))
    Await.result(job4, Duration("1 seconds"))
    Await.result(job5, Duration("1 seconds"))
    Await.result(job6, Duration("1 seconds"))
    Await.result(job7, Duration("1 seconds"))
    lazy val doing: Vector[Future[Any]] = Vector(job1, job2, job3, job4, job5, job6)
    //    toDo(doing)
  }
}
