package cleanArch.module.database

import cleanArch.domain.auth._
import cleanArch.domain.todo.Item

import scala.annotation.tailrec

class Holder {

  var itemMap: Map[Int, Map[Int, Item]] = Map(1 -> Map.empty)
  var userMap: Map[Int, Session] = Map.empty

  def createId(idMap: Map[Int, _]): Int = {
    idMap.toList match {
      case _ :: _ => idMap.last._1 + 1
      case Nil => 1
    }
  }

  def findUserIdByUsername(username: String): Int = {
    @tailrec
    def iterator(usersMap: Map[Int, Session], username: String): Int = {
      if (usersMap.isEmpty) {
        throw new NoSuchElementException()
      } else {
        if (usersMap.head._2.user.username.equals(username)) {
          usersMap.head._2.user.id
        } else {
          iterator(usersMap.tail, username)
        }
      }
    }

    iterator(userMap, username)
  }

  def addItem(userId: Int, item: Item): Unit = {
    val id = createId(if (itemMap.isEmpty) itemMap else itemMap(userId))
    val newItemMap = itemMap(userId) + (id -> item)
    itemMap = itemMap.updated(userId, newItemMap)
  }

  def getItem(userId: Int, id: Int): Option[Item] = {
    itemMap.getOrElse(userId, throw new NoSuchElementException()).get(id)
  }

  def editItem(userId: Int, id: Int, field: String, text: String): Unit = {
    val itemsMap = itemMap.getOrElse(userId, throw new NoSuchElementException())
    val newItem = field match {
      case "message" => itemsMap.getOrElse(id, throw new NoSuchElementException()).copy(message = text)
      case "done" =>
        val done = text == "true"
        itemsMap.getOrElse(id, throw new NoSuchElementException()).copy(done = done)
      case _ => throw new NoSuchFieldException()
    }
    val x = itemMap(userId) + (id -> newItem)
    itemMap = itemMap + (userId -> x)
  }

  def signUp(username: String, password: String): Unit = {
    val id = createId(userMap)
    val user = User(id, username, password)
    itemMap = itemMap + (id -> Map.empty)
    userMap = userMap + (id -> Session(user, isLogin = true))
  }

  def signIn(username: String, password: String): Unit = {
    val userId = findUserIdByUsername(username)
    val session = userMap(userId)
    if (session.user.password != password) {
      throw new NoSuchElementException()
    } else {
      val newSession = session.copy(isLogin = true)
      userMap = userMap + (userId -> newSession)
    }
  }

  def signOut(username: String): Unit = {
    val userId = findUserIdByUsername(username)
    val session = userMap(userId)
    val newSession = session.copy(isLogin = false)
    userMap = userMap + (userId -> newSession)
  }

  def findSession(username: String): Option[Session] = {
    userMap.get(findUserIdByUsername(username))
  }
}

object Holder extends Holder {
  def apply(): Holder = new Holder
}