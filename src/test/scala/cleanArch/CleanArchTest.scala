package cleanArch

import cleanArch.contract.service.auth._
import cleanArch.contract.service.todo._

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContextExecutor
import scala.language.postfixOps

class CleanArchTest extends munit.FunSuite {

  implicit val ec1: ExecutionContextExecutor = ExecutionContext fromExecutor Executors.newCachedThreadPool()

  import cleanArch.module.config.Config.ConfigOne._

  test("Main Test") {
    // init
    val firstItem = "Hello World!"
    val secondItem = "SALAM DONYA!"
    val thirdItem = "Welcome To Adanic Corp."
    val editMessage = "Message Changed"
    val firstUser = "Amir"
    val firsPass = "7990"
    //
    for {
      r1 <- signUpService call SignUpService.Request(firstUser, firsPass)
      r2 <- addItemService call AddItemService.Request(firstUser, firstItem, state = true)
      r3 <- addItemService call AddItemService.Request(firstUser, secondItem, state = true)
      r4 <- addItemService call AddItemService.Request(firstUser, thirdItem, state = true)
      r5 <- getItemService call GetItemService.Request(firstUser, 3)
      r6 <- editItemService call EditItemService.Request(firstUser, 3, "message", editMessage)
      r7 <- signOutService call SignOutService.Request(firstUser)
    } yield ()
  }
}
