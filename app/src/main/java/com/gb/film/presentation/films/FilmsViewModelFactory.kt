package com.gb.film.presentation.films

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gb.film.data.scheduler_provider.SchedulerProvider
import com.gb.film.domain.repository.FilmsRepository
import javax.inject.Inject

class FilmsViewModelFactory @Inject constructor(
    private val filmsRepository: FilmsRepository,
    private val schedulerProvider: SchedulerProvider
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FilmsViewModelImpl(filmsRepository, schedulerProvider) as T
    }
}