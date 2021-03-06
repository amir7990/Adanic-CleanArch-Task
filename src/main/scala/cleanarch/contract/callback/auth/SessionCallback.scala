package cleanarch.contract.callback.auth

import cleanarch.domain.auth.Session

import scala.concurrent.Future

abstract class SessionCallback {

  def add(userId: Long): Future[Session]

  def get(id: Long): Future[Option[Session]]

  def update(session: Session): Future[Unit]

  def remove(id: Long): Future[Unit]

}
