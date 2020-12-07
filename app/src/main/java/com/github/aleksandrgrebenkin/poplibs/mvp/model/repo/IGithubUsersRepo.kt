package com.github.aleksandrgrebenkin.poplibs.mvp.model.repo

import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubRepository
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun loadUsers(): Single<List<GithubUser>>
    fun loadUserRepos(url: String): Single<List<GithubRepository>>
}