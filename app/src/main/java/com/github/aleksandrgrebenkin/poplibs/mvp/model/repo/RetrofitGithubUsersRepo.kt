package com.github.aleksandrgrebenkin.poplibs.mvp.model.repo

import com.github.aleksandrgrebenkin.poplibs.mvp.model.api.IDataSource
import com.github.aleksandrgrebenkin.poplibs.mvp.model.cache.IUsersCache
import com.github.aleksandrgrebenkin.poplibs.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IUsersCache
) : IGithubUsersRepo {

    override fun loadUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    cache.putUsers(users).andThen(Single.just(users))
                }
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}