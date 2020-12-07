package com.github.aleksandrgrebenkin.poplibs.mvp.model.repo

import com.github.aleksandrgrebenkin.poplibs.mvp.model.api.IDataSource
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {

    override fun loadUsers() = api.getUsers().subscribeOn(Schedulers.io())

    override fun loadUserRepos(url: String) = api.getUserRepos(url).subscribeOn(Schedulers.io())
}