package com.github.aleksandrgrebenkin.poplibs.mvp.presenter

import com.github.aleksandrgrebenkin.poplibs.mvp.view.MainView
import com.github.aleksandrgrebenkin.poplibs.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backPressed() {
        router.exit()
    }
}