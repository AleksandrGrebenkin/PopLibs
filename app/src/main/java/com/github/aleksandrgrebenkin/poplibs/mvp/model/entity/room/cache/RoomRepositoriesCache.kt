package com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.cache

import com.github.aleksandrgrebenkin.poplibs.mvp.model.cache.IRepositoriesCache
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubRepository
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.RoomGithubRepository
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.database.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.lang.RuntimeException

class RoomRepositoriesCache(val db: Database) : IRepositoriesCache {
    override fun putRepositories(user: GithubUser, repositories: List<GithubRepository>) =
        Completable.fromCallable {
            val roomUser = user.login.let {
                db.userDao.findByLogin(it)
            } ?: throw RuntimeException("No such user in cache")
            val roomRepos = repositories.map { repo ->
                RoomGithubRepository(
                    repo.id ?: "",
                    repo.name ?: "",
                    repo.forksCount ?: 0,
                    repo.fullName ?: "",
                    roomUser.id
                )
            }
            db.repositoryDao.insert(roomRepos)
        }

    override fun getRepositories(user: GithubUser) = Single.fromCallable {
        val roomUser = user.login.let { db.userDao.findByLogin(it) }
            ?: throw RuntimeException("No such user in cache")
        db.repositoryDao.findForUser(roomUser.id)
            .map { GithubRepository(it.id, it.name, it.forksCount, it.fullName) }
    }
}