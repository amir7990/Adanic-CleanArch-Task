package cleanArch

import cleanArch.module.config.Config

class cleanArchTest extends munit.FunSuite {
  test("Main Test") {
    val config = Config.ConfigOne
    val firstItem = "Hello World!"
    val secondItem = "SALAM DONYA!"
    val thirdItem = "Welcome To Adanic Corp."
    val editMessage = "Message Changed"

    config.signUp("Amir", "1234")

    config.addItem("Amir", firstItem, state = true)
    config.addItem("Amir", secondItem, state = true)
    config.addItem("Amir", thirdItem, state = false)

    config.signOut("Amir")

    config.signIn("Amir", "1234")

    assert(config.getItem("Amir", 1).get.message == firstItem)
    assert(config.getItem("Amir", 2).get.message == secondItem)
    assert(config.getItem("Amir", 3).get.message == thirdItem)

    config.editItem("Amir", 1, "message", editMessage)
    assert(config.getItem("Amir", 1).get.message == editMessage)


  }
}
