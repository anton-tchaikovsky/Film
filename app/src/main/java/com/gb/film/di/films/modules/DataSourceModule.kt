package com.gb.film.di.films.modules

import com.gb.film.data.data_source.RemoteDataSource
import com.gb.film.data.data_source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataSourceModule {
    @Singleton
    @Binds
    fun remoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}