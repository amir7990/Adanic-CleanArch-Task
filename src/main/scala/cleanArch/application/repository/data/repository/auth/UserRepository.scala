package cleanArch.application.repository.data.repository.auth

import cleanArch.application.repository.data.adapter.Adapter
import cleanArch.contract.callback.auth.UserCallback
import cleanArch.domain.auth.User
import cleanArch.module.database.DatabaseModule
import cleanArch.module.database.DatabaseModule._
import scalikejdbc._

import scala.concurrent.{ExecutionContext, Future}

class UserRepository extends UserCallback with DatabaseModule{

  override def getUserByUsername(username: String)(implicit ec: ExecutionContext): Future[Option[User]] = Future {
    NamedDB(cleanArchDatabase) readOnly { implicit session =>
      sql"""
           SELECT * FROM User
           WHERE username = $username
         """.map(Adapter.user).single().apply()
    }
  }

  override def getUserById(id: Long)(implicit ec: ExecutionContext): Future[Option[User]] = Future {
    NamedDB(cleanArchDatabase) readOnly { implicit session =>
      sql"""
          SELECT * FROM User
          WHERE id = $id
          """.map(Adapter.user).single().apply()
    }
  }

  override def add(username: String, password: String)(implicit ec: ExecutionContext): Future[Int] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           INSERT INTO User
           (username, password) VALUES ($username, $password)
         """.update().apply()
    }
  }

  override def update(id: Long, user: User)(implicit ec: ExecutionContext): Future[Int] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           UPDATE User SET (username = ${user.username}, password = ${user.password})
           WHERE id = $id
         """.update().apply()
    }
  }

  override def remove(id: Long)(implicit ec: ExecutionContext): Future[Unit] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           DELETE FROM User WHERE id = $id
         """.update().apply()
    }
  }

}

object UserRepository extends UserRepository
