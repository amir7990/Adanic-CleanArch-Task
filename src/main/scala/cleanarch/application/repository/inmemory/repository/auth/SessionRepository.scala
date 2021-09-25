package cleanarch.application.repository.inmemory.repository.auth

import cleanarch.contract.callback.auth.SessionCallback
import cleanarch.domain.auth.Session
import cleanarch.module.inmemory.InMemoryModule

import scala.concurrent.Future

class SessionRepository extends SessionCallback with InMemoryModule[Session] {

  override val name: String = "Session"

  override def add(userId: Long): Future[Session] = {
    val session = Session(userId, isLogin = true)
    addElement(session)
  }

  override def update(session: Session): Future[Unit] = {
    updateElement(session, old => old.userId == session.userId)
  }

  override def get(id: Long): Future[Option[Session]] = Future {
    data.find(s => s.userId == id)
  }

  override def remove(id: Long): Future[Unit] = {
    removeElement(s => s.userId == id)
  }

}

object SessionRepository extends SessionRepository
