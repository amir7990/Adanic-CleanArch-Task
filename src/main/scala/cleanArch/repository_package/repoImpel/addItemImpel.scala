package cleanArch.repository_package.repoImpel
import cleanArch.repository_package.repository._
import cleanArch.database._
class addItemImpel(_db: Holder) extends addItemRepo {
  override val db: Holder = _db

  override def addItemInRepo(id: Int, text: String, state: Boolean): Unit = {
    val item = Items(id, text, state)
    db.addItem(item)
  }
}

object addItemImpel{
  def apply(_db: Holder): addItemImpel = new addItemImpel(_db)
}