package com.github.aleksandrgrebenkin.poplibs.mvp.presenter

import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import com.github.aleksandrgrebenkin.poplibs.mvp.view.UserView
import com.github.aleksandrgrebenkin.poplibs.ui.BackButtonListener
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UserPresenter(val user: GithubUser) :
    MvpPresenter<UserView>(),
    BackButtonListener {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLogin(user.login)
    }

    override fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
