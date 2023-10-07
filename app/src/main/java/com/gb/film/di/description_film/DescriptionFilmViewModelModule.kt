package com.gb.film.di.description_film

import com.gb.film.data.scheduler_provider.SchedulerProvider
import com.gb.film.domain.repository.FilmsRepository
import com.gb.film.presentation.description_film.DescriptionFilmViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DescriptionFilmViewModelModule {
    @Provides
    fun descriptionFilmViewModelFactory(
        filmsRepository: FilmsRepository,
        schedulerProvider: SchedulerProvider
    ): DescriptionFilmViewModelFactory = DescriptionFilmViewModelFactory(filmsRepository, schedulerProvider)
}