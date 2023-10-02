package com.gb.film.di.modules

import com.gb.film.data.repository_impl.FilmsRepositoryImpl
import com.gb.film.domain.repository.FilmsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun filmsRepository(filmsRepository: FilmsRepositoryImpl): FilmsRepository
}