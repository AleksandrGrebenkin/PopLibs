package com.github.aleksandrgrebenkin.poplibs.mvp.presenter

import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubRepository
import com.github.aleksandrgrebenkin.poplibs.mvp.view.RepositoryView
import com.github.aleksandrgrebenkin.poplibs.ui.BackButtonListener
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RepositoryPresenter(private val repository: GithubRepository) :
    MvpPresenter<RepositoryView>(),
    BackButtonListener {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showForksCount(repository.forksCount.toString())
    }

    override fun backPressed(): Boolean {
        router.exit()
        return true
    }
}