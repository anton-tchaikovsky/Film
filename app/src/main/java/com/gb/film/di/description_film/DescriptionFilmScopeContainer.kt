package com.gb.film.di.description_film

interface DescriptionFilmScopeContainer {
    fun initDescriptionFilmScope(): DescriptionFilmSubcomponent

    fun releaseDescriptionFilmScope()
}