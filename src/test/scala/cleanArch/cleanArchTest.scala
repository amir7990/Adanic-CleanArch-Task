package cleanArch

import cleanArch.contract.service.auth.{SignInService, SignOutService, SignUpService}
import cleanArch.contract.service.todo.{AddItemService, EditItemService, GetItemService}
import cleanArch.module.config.Config

class cleanArchTest extends munit.FunSuite {
  test("Main Test") {
    import Config.ConfigOne._
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

    signUpService call SignUpService.Request("Ali", "7990")
    addItemService call AddItemService.Request("Ali", firstItem, state = true)
    addItemService call AddItemService.Request("Ali", secondItem, state = true)
    addItemService call AddItemService.Request("Ali", thirdItem, state = true)

    editItemService call EditItemService.Request("Ali", 1, "message", editMessage)

    println(getItemService call GetItemService.Request("Ali", 1))

  }
}
