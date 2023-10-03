package com.gb.film.di.films

import android.content.Context
import com.gb.film.FilmApp
import com.gb.film.di.description_film.DescriptionFilmSubcomponent
import com.gb.film.presentation.films.FilmsActivity
import com.gb.film.di.films.modules.RepositoryModule
import com.gb.film.di.films.modules.DataSourceModule
import com.gb.film.di.films.modules.FilmsViewModelModule
import com.gb.film.di.films.modules.RetrofitModule
import com.gb.film.di.films.modules.SchedulerProviderModule
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
    fun descriptionFilmSubcomponent(): DescriptionFilmSubcomponent

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): AppComponent

    }

    fun inject(app: FilmApp)
    fun inject(filmsActivity: FilmsActivity)
}