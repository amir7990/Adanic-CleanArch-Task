package cleanArch.contract.service

import scala.concurrent.{ExecutionContext, Future}

abstract class Service[Request, Response] {

  def call(request: Request)(implicit ec: ExecutionContext): Future[Response]
}
