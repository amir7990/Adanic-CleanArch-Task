package cleanArch

import cleanArch.application.usecase._
import cleanArch.module._

class cleanArchTest extends munit.FunSuite {
  test("Main Test"){
    val db = Holder()
    val firstItem = "Hello World!"
    val secondItem = "SALAM DONYA!"
    val thirdItem = "Welcome To Adanic Corp."
    val editMessage = "Message Changed"
    val add = AddItemUseCase(db)
    val get = GetItemUseCase(db)
    val edit = EditItemUseCase(db)

    add.addItem(1, firstItem, state = true)
    add.addItem(2, secondItem, state = true)
    add.addItem(3, thirdItem, state = false)

    assert(get.getItem(1).message == firstItem)
    assert(get.getItem(2).message == secondItem)
    assert(get.getItem(3).message == thirdItem)

    edit.editItem(1, "message", editMessage)
    assert(get.getItem(1).message == editMessage)
  }

}
