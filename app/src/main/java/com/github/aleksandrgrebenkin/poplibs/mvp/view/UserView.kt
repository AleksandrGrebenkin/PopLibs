package com.github.aleksandrgrebenkin.poplibs.mvp.view

import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

@Skip
interface UserView : MvpView {
    fun showUser(user: GithubUser)
}