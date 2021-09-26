package cleanarch.contract.callback.todo

import cleanarch.domain.todo.Item

import scala.concurrent.Future

abstract class ItemCallback {

  def add(userId: Long, text: String, state: Boolean): Future[Item]

  def get(id: Long): Future[Option[Item]]

  def update(item: Item): Future[Unit]

  def remove(id: Long): Future[Unit]

}
