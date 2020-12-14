package com.github.aleksandrgrebenkin.poplibs.di.modules

import androidx.room.Room
import com.github.aleksandrgrebenkin.poplibs.mvp.model.cache.IRepositoriesCache
import com.github.aleksandrgrebenkin.poplibs.mvp.model.cache.IUsersCache
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.cache.RoomRepositoriesCache
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.cache.RoomUsersCache
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.database.Database
import com.github.aleksandrgrebenkin.poplibs.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IUsersCache = RoomUsersCache(database)

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IRepositoriesCache = RoomRepositoriesCache(database)
}