package com.gb.film.di.modules

import com.gb.film.data.scheduler_provider.SchedulerProvider
import com.gb.film.data.scheduler_provider.SchedulerProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface SchedulerProviderModule {
    @Singleton
    @Binds
    fun  schedulerProvider(schedulerProvider: SchedulerProviderImpl): SchedulerProvider
}