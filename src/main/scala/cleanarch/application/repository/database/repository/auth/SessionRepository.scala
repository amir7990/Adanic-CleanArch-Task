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
      val id = sql"""
           INSERT INTO Session
           (userId, isLogin) VALUES ($userId, 'TRUE')
         """.updateAndReturnGeneratedKey().apply()
      sql"""
          SELECT * FROM Session
          WHERE id = $id
          """.map(SessionAdapter.session).single().apply().get
    }
  }

  override def get(id: Long): Future[Option[Session]] = Future {
    NamedDB(cleanArchDatabase) readOnly { implicit session =>
      sql"""
          SELECT * FROM Session
          WHERE id = $id
          """.map(SessionAdapter.session).single().apply()
    }
  }

  override def update(session: Session): Future[Session] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit s =>
      sql"""
           UPDATE Session SET isLogin = ${session.isLogin}
           WHERE userId = ${session.userId}
         """.update().apply()
      sql"""
          SELECT * FROM Session
          WHERE userId = ${session.userId}
          """.map(SessionAdapter.session).single().apply().get
    }
  }

  override def remove(id: Long): Future[Unit] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           DELETE FROM Session WHERE id = $id
         """.update().apply()
    }
  }

}

object SessionRepository extends SessionRepository
