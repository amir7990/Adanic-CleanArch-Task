package cleanArch.application.repository.data.adapter

import cleanArch.domain.auth.{Session, User}
import cleanArch.domain.todo.Item
import scalikejdbc.WrappedResultSet

object Adapter {

  def user(wr: WrappedResultSet): User = {
    User(
      wr long "id",
      wr string "username",
      wr string "password"
    )
  }

  def item(wr: WrappedResultSet): Item = {
    Item(
      wr long "id",
      wr long "userId",
      wr string "message",
      wr boolean "done"
    )
  }

  def session(wr: WrappedResultSet): Session = {
    Session(
      wr long "userId",
      wr boolean "isLogin"
    )
  }

}
