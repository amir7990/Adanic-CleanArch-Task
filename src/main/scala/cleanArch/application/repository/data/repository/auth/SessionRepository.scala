package cleanArch.application.repository.data.repository.auth

import cleanArch.application.repository.data.adapter.Adapter
import cleanArch.contract.callback.auth.SessionCallback
import cleanArch.domain.auth.Session
import cleanArch.module.database.DatabaseModule
import cleanArch.module.database.DatabaseModule.cleanArchDatabase
import scalikejdbc._

import scala.concurrent.Future

class SessionRepository extends SessionCallback with DatabaseModule {

  override def add(userId: Long): Future[Int] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           INSERT INTO Session
           (userId, isLogin) VALUES ($userId)
         """.update().apply()
    }
  }

  override def get(id: Long): Future[Option[Session]] = Future {
    NamedDB(cleanArchDatabase) readOnly { implicit session =>
      sql"""
          SELECT * FROM Session
          WHERE id = $id
          """.map(Adapter.session).single().apply()
    }
  }

  override def update(session: Session): Future[Int] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit s =>
      sql"""
           UPDATE Session SET isLogin = ${session.isLogin}
           WHERE userId = ${session.userId}
         """.update().apply()
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
