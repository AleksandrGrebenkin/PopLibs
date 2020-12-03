package com.github.aleksandrgrebenkin.poplibs.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface RepositoriesView : MvpView {
    fun init()
    fun updateList()
    fun showError(message: Throwable)
}