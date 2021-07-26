package cleanArch.repositories

import cleanArch.DB.Items

class RepoImpel extends Callback {
  override def searchHelloWorld(id: Int): String = {
    val db = Items
    val strings = db.getItems
    strings.toMap.getOrElse(id, "NOT FOUND!")
  }
}
 object RepoImpel extends RepoImpel
