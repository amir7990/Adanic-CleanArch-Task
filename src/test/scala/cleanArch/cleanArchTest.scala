package cleanArch

import cleanArch.contract.service.auth.{SignInService, SignOutService, SignUpService}
import cleanArch.contract.service.todo.{AddItemService, GetItemService}
import cleanArch.module.config.Config

class cleanArchTest extends munit.FunSuite {
  test("Main Test") {
    import Config.ConfigOne._
    val config = Config.ConfigOne
    val firstItem = "Hello World!"
    val secondItem = "SALAM DONYA!"
    val thirdItem = "Welcome To Adanic Corp."
    val editMessage = "Message Changed"

    signUpService call SignUpService.Request("Amir", "7990")
    signOutService call SignOutService.Request("Amir")
    addItemService call AddItemService.Request("Amir", firstItem, state = true)
    addItemService call AddItemService.Request("Amir", secondItem, state = true)
    addItemService call AddItemService.Request("Amir", thirdItem, state = true)
    val x = getItemService call GetItemService.Request("Amir", 2)
    println(x.get.toString)
    //    signInService call SignInService.Request("Amir", "7990")
//    config.signUp("Amir", "1234")
    signUpService call SignUpService.Request("Ali", "7990")
    addItemService call AddItemService.Request("Ali", firstItem, state = true)
    addItemService call AddItemService.Request("Ali", secondItem, state = true)
    addItemService call AddItemService.Request("Ali", thirdItem, state = true)

    println(getItemService call GetItemService.Request("Ali", 2))
//    config.addItem("Amir", firstItem, state = true)
//    config.addItem("Amir", secondItem, state = true)
//    config.addItem("Amir", thirdItem, state = false)
//
//    config.signOut("Amir")
//
//    config.signIn("Amir", "1234")
//
//    assert(config.getItem("Amir", 1).getItemCallback.message == firstItem)
//    assert(config.getItem("Amir", 2).getItemCallback.message == secondItem)
//    assert(config.getItem("Amir", 3).getItemCallback.message == thirdItem)
//
//    config.editItem("Amir", 1, "message", editMessage)
//    assert(config.getItem("Amir", 1).getItemCallback.message == editMessage)

  }
}
