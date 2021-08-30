package cleanArch.application.repository.inmemory.repository.auth

import cleanArch.contract.callback.auth.SessionCallback
import cleanArch.domain.auth.Session
import cleanArch.module.inmemory.InMemoryModule

import scala.concurrent.Future

class SessionRepository extends SessionCallback with InMemoryModule[Session] {

  override def add(userId: Long): Future[Session] = {
    val session = Session(userId, isLogin = true)
    addElement(session)
  }

  override def update(session: Session): Future[Session] = {
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