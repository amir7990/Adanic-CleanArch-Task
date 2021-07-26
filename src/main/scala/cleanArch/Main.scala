package cleanArch
import cleanArch.useCases.UseCase._
import cleanArch.DB._

class Main

object Main{
  def main(args: Array[String]): Unit = {
    val db = new Holder
    val add = addItemUseCase(db)
    val get = getItemUseCase(db)
    add.addItem(1, "hello world", state = true)
    println(get.getItem(1).message)
  }
}


