package cleanarch.application.repository.database.adapter.todo

import cleanarch.domain.todo.Item
import scalikejdbc.WrappedResultSet

object ItemAdapter {

  def item(wr: WrappedResultSet): Item = {
    Item(
      wr long "id",
      wr long "userId",
      wr string "message",
      wr boolean "done"
    )
  }

}
