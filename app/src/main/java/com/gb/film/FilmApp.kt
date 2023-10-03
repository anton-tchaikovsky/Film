package com.gb.film

import android.app.Application
import com.gb.film.di.films.AppComponent
import com.gb.film.di.description_film.DescriptionFilmScopeContainer
import com.gb.film.di.description_film.DescriptionFilmSubcomponent
import com.gb.film.di.films.DaggerAppComponent

class FilmApp: Application(), DescriptionFilmScopeContainer {
    lateinit var appComponent: AppComponent

    private var descriptionFilmSubcomponent: DescriptionFilmSubcomponent? = null

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

    override fun initDescriptionFilmScope() =
        appComponent.descriptionFilmSubcomponent().also {
            descriptionFilmSubcomponent = it
        }

    override fun releaseDescriptionFilmScope() {
        descriptionFilmSubcomponent = null
    }

    companion object {
        lateinit var instance: FilmApp
    }
}