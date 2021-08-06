package cleanArch.domain.auth

case class Session(userId: Int, isLogin: Boolean) {

  def updateState(state: Boolean): Session = {
    copy(isLogin = state)
  }
}
