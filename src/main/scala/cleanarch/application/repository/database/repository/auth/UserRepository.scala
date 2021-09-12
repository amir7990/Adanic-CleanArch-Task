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
    object User extends SQLSyntaxSupport[User]
    val u = User.syntax("u")
    NamedDB(cleanArchDatabase) readOnly { implicit session =>
      println("salam")
      withSQL{
        select.from(User as u).where.eq(u.username, username)
      }.map(UserAdapter.user).single().apply()
    }
  }

  override def getUserById(id: Long): Future[Option[User]] = Future {
    NamedDB(cleanArchDatabase) readOnly { implicit session =>
      sql"""
          SELECT * FROM User
          WHERE id = $id
          """.map(UserAdapter.user).single().apply()
    }
  }

  override def add(username: String, password: String): Future[User] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      val id = sql"""
           INSERT INTO User
           (username, password) VALUES ($username, $password)
         """.updateAndReturnGeneratedKey().apply()
      sql"""
          SELECT * FROM User
          WHERE id = $id
          """.map(UserAdapter.user).single().apply().get
    }
  }

  override def update(id: Long, user: User): Future[User] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           UPDATE User SET (username = ${user.username}, password = ${user.password})
           WHERE id = $id
         """.update().apply()
      sql"""
          SELECT * FROM User
          WHERE id = $id
          """.map(UserAdapter.user).single().apply().get
    }
  }

  override def remove(id: Long): Future[Unit] = Future {
    NamedDB(cleanArchDatabase) localTx { implicit session =>
      sql"""
           DELETE FROM User WHERE id = $id
         """.update().apply()
    }
  }

}

object UserRepository extends UserRepository

