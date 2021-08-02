package cleanArch

import cleanArch.module.config.Config

class cleanArchTest extends munit.FunSuite {
  test("Main Test") {
    val config = Config.ConfigOne
    val firstItem = "Hello World!"
    val secondItem = "SALAM DONYA!"
    val thirdItem = "Welcome To Adanic Corp."
    val editMessage = "Message Changed"

    config.addItem(1, firstItem, state = true)
    config.addItem(2, secondItem, state = true)
    config.addItem(3, thirdItem, state = false)

    assert(config.getItem(1).get.message == firstItem)
    assert(config.getItem(2).get.message == secondItem)
    assert(config.getItem(3).get.message == thirdItem)

    config.editItem(1, "message", editMessage)
    assert(config.getItem(1).get.message == editMessage)
  }
}
