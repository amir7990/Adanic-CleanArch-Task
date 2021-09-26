package cleanarch

import cleanarch.contract.service.auth._
import cleanarch.contract.service.todo._
import cleanarch.module.config.ConfigModule
import org.slf4j._

import java.util.concurrent.Executors
import scala.concurrent._
import scala.util.Success
import scala.util.Try
import scala.util.Failure

class CleanArchTest extends munit.FunSuite {

  implicit val ec1: ExecutionContextExecutor = ExecutionContext fromExecutor Executors.newCachedThreadPool()

  val logger: Logger = LoggerFactory.getLogger(classOf[CleanArchTest])

  val WAIT_TIME: Int = 100

  val toDo : ConfigModule = ConfigModule.ConfigModuleOne

  test("Main Test") {
    // init
    val firstItem = "Hello World!"
//    val secondItem = "SALAM DONYA!"
//    val thirdItem = "Welcome To Adanic Corp."
//    val editMessage = "Message Changed"
    val firstUser = "Amir"
    val firsPass = "7990"
    //

    for {
      _ <- toDo.signUpService.call(SignUpService.Request(firstUser, firsPass)).transform {
        case Success(value) => Try {
          println(value.toString)
        }
        case Failure(exception) => Try {
          println(exception.getMessage)
        }
      }

      _ <- toDo.signInService.call(SignInService.Request(firstUser, firsPass)).transform {
        case Success(value) => Try {
          println(value.toString)
        }
        case Failure(exception) => Try {
          println(exception.getMessage)
        }
      }

      _ <- toDo.addItemService.call(AddItemService.Request(firstUser, firstItem, state = false)).transform {
        case Success(value) => Try {
          println(value.toString)
        }
        case Failure(exception) => Try {
          println(exception.getMessage)
        }
      }

      _ <- toDo.signOutService.call(SignOutService.Request(firstUser)).transform {
        case Success(value) => Try {
          println(value.toString)
        }
        case Failure(exception) => Try {
          println(exception.getMessage)
        }
      }
    }yield()


  }
}

