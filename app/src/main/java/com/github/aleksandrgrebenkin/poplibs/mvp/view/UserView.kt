package com.github.aleksandrgrebenkin.poplibs.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface UserView : MvpView {
    fun showLogin(login: String)
}