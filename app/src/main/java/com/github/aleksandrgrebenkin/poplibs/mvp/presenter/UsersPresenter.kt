package com.github.aleksandrgrebenkin.poplibs.mvp.presenter

import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import com.github.aleksandrgrebenkin.poplibs.mvp.model.repo.IGithubUsersRepo
import com.github.aleksandrgrebenkin.poplibs.mvp.presenter.list.IUserListPresenter
import com.github.aleksandrgrebenkin.poplibs.mvp.view.UsersView
import com.github.aleksandrgrebenkin.poplibs.mvp.view.list.UserItemView
import com.github.aleksandrgrebenkin.poplibs.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersPresenter() : MvpPresenter<UsersView>() {

    @Inject
    lateinit var usersRepo: IGithubUsersRepo
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var uiScheduler: Scheduler

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            user.avatarUrl?.let { view.loadImage(it) }
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()
    var loadUsersDisposable: Disposable? = null


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(Screens.RepositoriesScreen(user))
        }
    }

    private fun loadData() {
        loadUsersDisposable = usersRepo.loadUsers()
            .observeOn(uiScheduler)
            .subscribe(
                { users ->
                    usersListPresenter.users.clear()
                    usersListPresenter.users.addAll(users)
                    viewState.updateList()
                },
                { error ->
                    viewState.showError(error)
                }
            )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        loadUsersDisposable?.dispose()
    }
}