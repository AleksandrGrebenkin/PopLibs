package com.github.aleksandrgrebenkin.poplibs.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView : MvpView {
    fun showLogin(login: String)
}