package com.gb.film

import android.app.Application
import com.gb.film.di.AppComponent
import com.gb.film.di.DaggerAppComponent

class FilmApp: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = createAppComponent()
        appComponent.inject(this)
    }

    private fun createAppComponent() =
        DaggerAppComponent.builder()
            .applicationContext(this)
            .build()

    companion object {
        lateinit var instance: FilmApp
    }
}