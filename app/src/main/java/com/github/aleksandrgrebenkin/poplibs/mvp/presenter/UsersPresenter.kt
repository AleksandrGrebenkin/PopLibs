package com.github.aleksandrgrebenkin.poplibs.mvp.presenter

import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import com.github.aleksandrgrebenkin.poplibs.mvp.model.repo.GithubUserRepo
import com.github.aleksandrgrebenkin.poplibs.mvp.presenter.list.IUserListPresenter
import com.github.aleksandrgrebenkin.poplibs.mvp.view.UsersView
import com.github.aleksandrgrebenkin.poplibs.mvp.view.list.UserItemView
import com.github.aleksandrgrebenkin.poplibs.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    private val usersRepo: GithubUserRepo,
    private val router: Router,
    private val mainThreadScheduler: Scheduler
) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(Screens.UserScreen(user))
        }
    }

    private fun loadData() {
        usersRepo.loadUser()
            .subscribeOn(Schedulers.io())
            .observeOn(mainThreadScheduler)
            .subscribe(
                { users -> usersListPresenter.users.addAll(users) },
                { error -> viewState.showError(error) },
                { viewState.updateList() }
            )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}