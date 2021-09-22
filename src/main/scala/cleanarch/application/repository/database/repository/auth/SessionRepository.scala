package cleanarch.application.repository.database.repository.auth

import cleanarch.application.repository.database.adapter.auth.SessionAdapter
import cleanarch.contract.callback.auth.SessionCallback
import cleanarch.domain.auth.Session
import cleanarch.module.database.DatabaseModule
import cleanarch.module.database.DatabaseModule.cleanArchDatabase
import scalikejdbc._

import scala.concurrent.Future

class SessionRepository extends SessionCallback with DatabaseModule {

  override def add(userId: Long): Future[Session] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           INSERT INTO sessions
           (userId) VALUES ($userId)
         """.updateAndReturnGeneratedKey().apply()
      Session(userId, isLogin = true)
    }
  }

  override def get(userId: Long): Future[Option[Session]] = Future {
    NamedDB(cleanArchDatabase) readOnly { implicit session =>
      sql"""
          SELECT * FROM sessions
          WHERE userId = $userId
          """.map(SessionAdapter.session).single().apply()
    }
  }

  override def update(session: Session): Future[Unit] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit s =>
      val successor =
        sql"""
           UPDATE sessions SET isLogin = ${session.isLogin}
           WHERE userId = ${session.userId}
         """.update().apply()
      successor match {
        case 1 => Future successful Unit
        case 0 => Future failed new Exception(s"Session Not Found")
        case _ => Future failed new Exception(s"Internal Server Error")
      }
    }
  }

  override def remove(id: Long): Future[Unit] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           DELETE FROM sessions WHERE id = $id
         """.update().apply()
    }
  }

}

object SessionRepository extends SessionRepository
