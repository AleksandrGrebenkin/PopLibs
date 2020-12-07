package com.github.aleksandrgrebenkin.poplibs.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface RepositoryView : MvpView {
    fun showForksCount(count: String)
}