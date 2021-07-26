package cleanArch.repositories.RepoImpel

import cleanArch.DB.Items
import cleanArch.repositories.Repository.getItemRepo

class getItemImpel extends getItemRepo {
  override def searchHelloWorld(id: Int): String = {
    val db = Items
    val strings = db.getItems
    strings.toMap.getOrElse(id, "NOT FOUND!")
  }
}
 object getItemImpel extends getItemImpel
