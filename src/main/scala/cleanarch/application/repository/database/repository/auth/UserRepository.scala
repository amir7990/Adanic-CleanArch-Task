package cleanarch.application.repository.database.repository.auth

import cleanarch.application.repository.database.adapter.auth.UserAdapter
import cleanarch.contract.callback.auth.UserCallback
import cleanarch.domain.auth.User
import cleanarch.module.database.DatabaseModule
import cleanarch.module.database.DatabaseModule._
import scalikejdbc._

import scala.concurrent.Future

class UserRepository extends UserCallback with DatabaseModule{

  override def getUserByUsername(username: String): Future[Option[User]] = Future {
    NamedDB(cleanArchDatabase) readOnly { implicit session =>
      sql"""
           select * from users where username = $username
         """.map(UserAdapter.user).single().apply()
    }
  }

  override def getUserById(id: Long): Future[Option[User]] = Future {
    NamedDB(cleanArchDatabase) readOnly { implicit session =>
      sql"""
          SELECT * FROM users
          WHERE id = $id
          """.map(UserAdapter.user).single().apply()
    }
  }

  override def add(username: String, password: String): Future[User] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      val id = sql"""
           INSERT INTO users
           (username, password) VALUES ($username, $password)
         """.updateAndReturnGeneratedKey().apply()
      User(id, username, password)
    }
  }

  override def update(id: Long, user: User): Future[Unit] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      val successor = sql"""
           UPDATE users SET (username = ${user.username}, password = ${user.password})
           WHERE id = $id
         """.update().apply()
      successor match {
        case 1 => Future successful Unit
        case 0 => Future failed new Exception(s"User Not Found")
        case _ => Future failed new Exception(s"Internal Server Error")
      }
    }
  }

  override def remove(id: Long): Future[Unit] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           DELETE FROM users WHERE id = $id
         """.update().apply()
    }
  }

}

object UserRepository extends UserRepository
