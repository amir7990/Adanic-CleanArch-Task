package cleanArch

import cleanArch.contract.service.auth._
import cleanArch.contract.service.todo._

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.Future
import scala.language.postfixOps
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scala.util._

class CleanArchTest extends munit.FunSuite {

  implicit val ec1: ExecutionContextExecutor = ExecutionContext fromExecutor Executors.newCachedThreadPool()

  val logger: Logger = LoggerFactory.getLogger(classOf[CleanArchTest])

  import cleanArch.module.config.Config.ConfigOne._

  def doThis(job: Future[Any]): Future[Unit] = {
    job.transform {
      case Success(value) => Try {
        logger.info(value.toString)
      }
      case Failure(exception) => Try {
        logger.error(exception.getMessage)
      }
    }
  }

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
      r1 <- doThis(signUpService call SignUpService.Request(firstUser, firsPass))
      r2 <- doThis(addItemService call AddItemService.Request(firstUser, firstItem, state = true))
      r3 <- doThis(addItemService call AddItemService.Request(firstUser, secondItem, state = true))
      r4 <- doThis(addItemService call AddItemService.Request(firstUser, thirdItem, state = true))
      r5 <- doThis(getItemService call GetItemService.Request(firstUser, 3))
      r6 <- doThis(editItemService call EditItemService.Request(firstUser, 3, "message", editMessage))
      r7 <- doThis(signOutService call SignOutService.Request(firstUser))
    } yield ()
  }
}
