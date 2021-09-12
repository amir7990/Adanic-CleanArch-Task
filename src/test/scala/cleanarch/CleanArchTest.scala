package cleanarch

import cleanarch.contract.service.auth._
import cleanarch.contract.service.todo._
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.lang.Thread.sleep
import java.util.concurrent.Executors
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutor}
import scala.language.postfixOps

class CleanArchTest extends munit.FunSuite {

  implicit val ec1: ExecutionContextExecutor = ExecutionContext fromExecutor Executors.newCachedThreadPool()

  val WAIT_TIME: Int = 10

  import cleanarch.module.config.ConfigModule.ConfigModuleOne._

  test("Main Test") {
    // init
    val firstItem = "Hello World!"
    val secondItem = "SALAM DONYA!"
    val thirdItem = "Welcome To Adanic Corp."
    val editMessage = "Message Changed"
    val firstUser = "Amir"
    val firsPass = "7990"
    //
    Await.result(signUpService call SignUpService.Request(firstUser, firsPass), Duration("1 seconds"))
    signUpService call SignUpService.Request(firstUser, firsPass)
    sleep(WAIT_TIME)
    addItemService call AddItemService.Request(firstUser, firstItem, state = true)
    sleep(WAIT_TIME)
    addItemService call AddItemService.Request(firstUser, secondItem, state = true)
    sleep(WAIT_TIME)
    addItemService call AddItemService.Request(firstUser, thirdItem, state = true)
    sleep(WAIT_TIME)
    getItemService call GetItemService.Request(firstUser, 3)
    sleep(WAIT_TIME)
    editItemService call EditItemService.Request(firstUser, 3, "message", editMessage)
    sleep(WAIT_TIME)
    signOutService call SignOutService.Request(firstUser)

  }
}

