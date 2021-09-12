package cleanarch.application.repository.database.adapter.auth

import cleanarch.domain.auth.Session
import scalikejdbc.WrappedResultSet

object SessionAdapter {

  def session(wr: WrappedResultSet): Session = {
    Session(
      wr long "userId",
      wr boolean "isLogin"
    )
  }

}
