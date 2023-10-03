package com.gb.film.presentation.films

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.film.data.scheduler_provider.SchedulerProvider
import com.gb.film.domain.entity.films.Result
import com.gb.film.domain.repository.FilmsRepository
import com.gb.film.utility.SingleEventLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class FilmsViewModelImpl @Inject constructor(
    private val filmsRepository: FilmsRepository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel(), FilmsViewModel {
    private var compositeDisposable = CompositeDisposable()

    private val _filmsLiveData: MutableLiveData<FilmsState> = MutableLiveData()

    private val _errorLiveData: SingleEventLiveData<String> = SingleEventLiveData()

    private var page = START_PAGE

    private val filmsList: MutableList<Result> = mutableListOf()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()

    }

    override val filmsLiveData: LiveData<FilmsState>
        get() = _filmsLiveData

    override val errorLiveData: SingleEventLiveData<String>
        get() = _errorLiveData

    override fun getFilms() {
        compositeDisposable.add(
            filmsRepository.getFilms(page)
                .subscribeOn(schedulerProvider.ioScheduler())
                .observeOn(schedulerProvider.mainScheduler())
                .doOnSubscribe { _filmsLiveData.postValue(FilmsState.Loading) }
                .subscribeBy(
                    onSuccess = {
                        filmsList.addAll(it.results)
                        _filmsLiveData.value = FilmsState.Success(filmsList)
                    },
                    onError = {
                        _errorLiveData.value = it.message ?: DEFAULT_ERROR
                    })
        )
    }

    override fun onClickFilm(position: Int) {
        _filmsLiveData.value = FilmsState.DescriptionFilm(filmsList[position].id)
    }

    companion object{
        private const val DEFAULT_ERROR = "Default error"
        private const val START_PAGE = 1
    }
}