package cleanArch.domain.auth

case class Session(userId: Long, isLogin: Boolean) {

  def updateState(state: Boolean): Session = {
    copy(isLogin = state)
  }

}
