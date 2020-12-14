package com.github.aleksandrgrebenkin.poplibs.ui

import android.app.Application
import com.github.aleksandrgrebenkin.poplibs.di.AppComponent
import com.github.aleksandrgrebenkin.poplibs.di.DaggerAppComponent
import com.github.aleksandrgrebenkin.poplibs.di.modules.AppModule
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.database.Database
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}