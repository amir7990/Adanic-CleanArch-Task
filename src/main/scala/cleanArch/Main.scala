package cleanArch
import cleanArch.useCases.UseCase.getItemUseCase

class Main

object Main{
  def main(args: Array[String]): Unit = {
    val string = getItemUseCase.getHelloWorld(2)
    println(string)
  }
}


