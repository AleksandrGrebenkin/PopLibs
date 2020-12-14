package com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.RoomGithubRepository
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.RoomGithubUser
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.dao.RepositoryDao
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.dao.UserDao
import java.lang.RuntimeException

@androidx.room.Database(
    entities = [RoomGithubUser::class, RoomGithubRepository::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null
    }
}