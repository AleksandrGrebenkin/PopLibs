package com.github.aleksandrgrebenkin.poplibs.navigation

import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubRepository
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import com.github.aleksandrgrebenkin.poplibs.ui.fragment.RepositoriesFragment
import com.github.aleksandrgrebenkin.poplibs.ui.fragment.RepositoryFragment
import com.github.aleksandrgrebenkin.poplibs.ui.fragment.UserFragment
import com.github.aleksandrgrebenkin.poplibs.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen() : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen(val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }

    class RepositoriesScreen(val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = RepositoriesFragment.newInstance(user)
    }

    class RepositoryScreen(val repository: GithubRepository) : SupportAppScreen() {
        override fun getFragment() = RepositoryFragment.newInstance(repository)
    }
}