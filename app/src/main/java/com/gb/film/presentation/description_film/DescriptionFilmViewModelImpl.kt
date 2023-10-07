package com.gb.film.presentation.description_film

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.film.data.scheduler_provider.SchedulerProvider
import com.gb.film.domain.entity.description_film.DescriptionFilm
import com.gb.film.domain.repository.FilmsRepository
import com.gb.film.utility.SingleEventLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class DescriptionFilmViewModelImpl @Inject constructor(
    private val filmsRepository: FilmsRepository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel(), DescriptionFilmViewModel {
    private var compositeDisposable = CompositeDisposable()

    private val _errorLiveData: SingleEventLiveData<String> = SingleEventLiveData()

    private val _descriptionFilm: MutableLiveData<DescriptionFilm> = MutableLiveData()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()

    }

    override val descriptionFilmLiveData: LiveData<DescriptionFilm>
        get() = _descriptionFilm

    override val errorLiveData: SingleEventLiveData<String>
        get() = _errorLiveData

    override fun getDescriptionFilm(idFilm: Int) {
        compositeDisposable.add(
            filmsRepository.getDescriptionFilm(idFilm)
                .subscribeOn(schedulerProvider.ioScheduler())
                .observeOn(schedulerProvider.mainScheduler())
                .subscribeBy(
                    onSuccess = {
                        _descriptionFilm.value = it
                    },
                    onError = {
                        _errorLiveData.value = it.message ?: DEFAULT_ERROR
                    })
        )
    }

    companion object{
        private const val DEFAULT_ERROR = "Default error"
    }
}