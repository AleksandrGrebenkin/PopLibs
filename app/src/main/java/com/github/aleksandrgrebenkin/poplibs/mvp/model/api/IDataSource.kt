package com.github.aleksandrgrebenkin.poplibs.mvp.model.api

import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubRepository
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface IDataSource {

    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Single<GithubUser>

    @GET
    fun getUserRepos(@Url url: String): Single<List<GithubRepository>>

}