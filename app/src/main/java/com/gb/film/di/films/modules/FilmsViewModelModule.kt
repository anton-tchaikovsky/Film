package com.gb.film.di.films.modules

import com.gb.film.data.scheduler_provider.SchedulerProvider
import com.gb.film.domain.repository.FilmsRepository
import com.gb.film.presentation.films.FilmsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FilmsViewModelModule {
    @Provides
    fun filmsViewModelFactory(
        filmsRepository: FilmsRepository,
        schedulerProvider: SchedulerProvider
    ): FilmsViewModelFactory = FilmsViewModelFactory(filmsRepository, schedulerProvider)
}