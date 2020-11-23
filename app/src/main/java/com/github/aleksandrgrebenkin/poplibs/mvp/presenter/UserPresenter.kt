package com.github.aleksandrgrebenkin.poplibs.mvp.presenter

import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import com.github.aleksandrgrebenkin.poplibs.mvp.view.UserView
import com.github.aleksandrgrebenkin.poplibs.ui.BackButtonListener
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(private val user: GithubUser, private val router: Router) :
    MvpPresenter<UserView>(),
    BackButtonListener {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showUser(user)
    }


    override fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
