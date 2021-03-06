package cleanarch.contract.service

import scala.concurrent._

abstract class Service[Request, Response] {

  def call(request: Request)(implicit ec: ExecutionContext): Future[Response]
}
