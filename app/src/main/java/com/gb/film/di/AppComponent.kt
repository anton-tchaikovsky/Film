package com.gb.film.di

import android.content.Context
import com.gb.film.FilmApp
import com.gb.film.presentation.FilmsActivity
import com.gb.film.di.modules.RepositoryModule
import com.gb.film.di.modules.DataSourceModule
import com.gb.film.di.modules.FilmsViewModelModule
import com.gb.film.di.modules.RetrofitModule
import com.gb.film.di.modules.SchedulerProviderModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RetrofitModule::class,
    DataSourceModule::class,
    RepositoryModule::class,
    FilmsViewModelModule::class,
    SchedulerProviderModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): AppComponent

    }

    fun inject(app: FilmApp)
    fun inject(filmsActivity: FilmsActivity)

}