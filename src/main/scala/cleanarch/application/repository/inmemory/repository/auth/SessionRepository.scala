package cleanarch.application.repository.inmemory.repository.auth

import cleanarch.contract.callback.auth.SessionCallback
import cleanarch.domain.auth.Session
import cleanarch.module.inmemory.InMemoryModule

import scala.concurrent.Future

class SessionRepository extends SessionCallback with InMemoryModule[Session] {

  override def add(userId: Long): Future[Session] = {
    val session = Session(userId, isLogin = true)
    addElement(session)
  }

  override def update(session: Session): Future[Unit] = {
    updateElement(session.userId, session)
  }

  override def get(id: Long): Future[Option[Session]] = {
    getElement(id)
  }

  override def remove(id: Long): Future[Unit] = {
    removeElement(id)
  }

}

object SessionRepository extends SessionRepository
