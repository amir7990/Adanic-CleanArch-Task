package cleanarch.application.repository.database.adapter.auth

import cleanarch.domain.auth.User
import scalikejdbc.WrappedResultSet

object UserAdapter {

  def user(wr: WrappedResultSet): User = {
    User(
      wr long "id",
      wr string "username",
      wr string "password"
    )
  }

}
