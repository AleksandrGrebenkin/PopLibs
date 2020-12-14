package com.github.aleksandrgrebenkin.poplibs.di.modules

import com.github.aleksandrgrebenkin.poplibs.mvp.model.api.IDataSource
import com.github.aleksandrgrebenkin.poplibs.mvp.model.cache.IRepositoriesCache
import com.github.aleksandrgrebenkin.poplibs.mvp.model.cache.IUsersCache
import com.github.aleksandrgrebenkin.poplibs.mvp.model.network.INetworkStatus
import com.github.aleksandrgrebenkin.poplibs.mvp.model.repo.IGithubRepositoriesRepo
import com.github.aleksandrgrebenkin.poplibs.mvp.model.repo.IGithubUsersRepo
import com.github.aleksandrgrebenkin.poplibs.mvp.model.repo.RetrofitGithubRepositoriesRepo
import com.github.aleksandrgrebenkin.poplibs.mvp.model.repo.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUsersCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRepositoriesCache
    ): IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
}