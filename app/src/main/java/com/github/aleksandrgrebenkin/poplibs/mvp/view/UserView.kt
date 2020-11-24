package com.github.aleksandrgrebenkin.poplibs.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

@Skip
interface UserView : MvpView {
    fun showLogin(login: String)
}