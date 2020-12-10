package com.github.aleksandrgrebenkin.poplibs.mvp.model.repo

import com.github.aleksandrgrebenkin.poplibs.mvp.model.api.IDataSource
import com.github.aleksandrgrebenkin.poplibs.mvp.model.cache.IRepositoriesCache
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubRepository
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.RoomGithubRepository
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.database.Database
import com.github.aleksandrgrebenkin.poplibs.mvp.model.network.INetworkStatus
import com.github.aleksandrgrebenkin.poplibs.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IRepositoriesCache
) : IGithubRepositoriesRepo {
    override fun loadUserRepos(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getUserRepos(url)
                        .flatMap { repositories ->
                            cache.putRepositories(user, repositories).andThen(Single.just(repositories))
                        }
                } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url"))
                    .subscribeOn(Schedulers.io())
            } else {
                cache.getRepositories(user)
            }
        }.subscribeOn(Schedulers.io())
}