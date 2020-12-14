package com.github.aleksandrgrebenkin.poplibs.di

import com.github.aleksandrgrebenkin.poplibs.di.modules.*
import com.github.aleksandrgrebenkin.poplibs.mvp.presenter.*
import com.github.aleksandrgrebenkin.poplibs.ui.activity.MainActivity
import com.github.aleksandrgrebenkin.poplibs.ui.fragment.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        ImageModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(usersFragment: UsersFragment)

    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoriesPresenter: RepositoriesPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}