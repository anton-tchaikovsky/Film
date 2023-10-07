package com.gb.film.presentation.description_film

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gb.film.data.scheduler_provider.SchedulerProvider
import com.gb.film.domain.repository.FilmsRepository
import javax.inject.Inject

class DescriptionFilmViewModelFactory @Inject constructor(
    private val filmsRepository: FilmsRepository,
    private val schedulerProvider: SchedulerProvider,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DescriptionFilmViewModelImpl(filmsRepository, schedulerProvider) as T
    }
}