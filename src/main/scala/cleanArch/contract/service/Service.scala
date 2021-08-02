package cleanArch.contract.service

abstract class Service[Request, Response] {
  def call(request: Request): Response
}
