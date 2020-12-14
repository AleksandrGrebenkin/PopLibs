package com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.cache

import com.github.aleksandrgrebenkin.poplibs.mvp.model.cache.IUsersCache
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.RoomGithubUser
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.database.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomUsersCache(val db: Database) : IUsersCache {
    override fun putUsers(users: List<GithubUser>) = Completable.fromCallable {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                user.id ?: "",
                user.login ?: "",
                user.avatarUrl ?: "",
                user.reposUrl ?: ""
            )
        }
        db.userDao.insert(roomUsers)
    }

    override fun getUsers() = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
        }
    }
}