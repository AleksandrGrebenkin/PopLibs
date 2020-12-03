package com.github.aleksandrgrebenkin.poplibs.mvp.presenter

import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubRepository
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import com.github.aleksandrgrebenkin.poplibs.mvp.model.repo.IGithubUsersRepo
import com.github.aleksandrgrebenkin.poplibs.mvp.presenter.list.IRepositoryListPresenter
import com.github.aleksandrgrebenkin.poplibs.mvp.view.RepositoriesView
import com.github.aleksandrgrebenkin.poplibs.mvp.view.list.RepositoryItemView
import com.github.aleksandrgrebenkin.poplibs.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepositoriesPresenter(
    private val usersRepo: IGithubUsersRepo,
    private val user: GithubUser,
    private val router: Router,
    private val uiScheduler: Scheduler
) : MvpPresenter<RepositoriesView>() {

    class RepositoriesListPresenter : IRepositoryListPresenter {
        val repositories = mutableListOf<GithubRepository>()

        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            view.setName(repository.name)
        }

        override fun getCount() = repositories.size
    }

    val repositoriesListPresenter = RepositoriesListPresenter()
    var loadRepositoriesDisposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        repositoriesListPresenter.itemClickListener = { itemView ->
            val repository = repositoriesListPresenter.repositories[itemView.pos]
            router.navigateTo(Screens.RepositoryScreen(repository))
        }
    }

    private fun loadData() {
        loadRepositoriesDisposable = usersRepo.loadUserRepos(user.reposUrl)
            .observeOn(uiScheduler)
            .subscribe(
                { repositories ->
                    repositoriesListPresenter.repositories.clear()
                    repositoriesListPresenter.repositories.addAll(repositories)
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
        loadRepositoriesDisposable?.dispose()
    }
}