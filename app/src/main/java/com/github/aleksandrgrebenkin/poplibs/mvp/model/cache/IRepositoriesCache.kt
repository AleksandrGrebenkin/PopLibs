package com.github.aleksandrgrebenkin.poplibs.mvp.model.cache

import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubRepository
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRepositoriesCache {
    fun putRepositories(user: GithubUser, repositories: List<GithubRepository>): Completable
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}