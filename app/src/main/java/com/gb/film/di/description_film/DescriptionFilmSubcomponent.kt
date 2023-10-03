package com.gb.film.di.description_film

import com.gb.film.presentation.description_film.DescriptionFilmFragment
import dagger.Subcomponent

@DescriptionFilmScope
@Subcomponent(
    modules = [
        DescriptionFilmViewModelModule::class
    ]
)
interface DescriptionFilmSubcomponent {
    fun inject(descriptionFilmFragment: DescriptionFilmFragment)
}