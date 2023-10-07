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

    private val _isLastPageLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    private val _filmsLiveData: MutableLiveData<FilmsState> = MutableLiveData()

    private val _errorLiveData: SingleEventLiveData<String> = SingleEventLiveData()

    private var nextPage = START_PAGE

    private var lastPage: Int = 1

    private val filmsList: MutableList<Result> = mutableListOf()

    private var isShowNotConnectError = true

    private var isShowError = true

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    override val filmsLiveData: LiveData<FilmsState>
        get() = _filmsLiveData

    override val isLastPageLiveData: LiveData<Boolean>
        get() = _isLastPageLiveData

    override val errorLiveData: SingleEventLiveData<String>
        get() = _errorLiveData

    override fun getFilms() {
        if (filmsRepository.isConnect) {
            isShowNotConnectError = true
            compositeDisposable.add(
                filmsRepository.getFilms(nextPage)
                    .subscribeOn(schedulerProvider.ioScheduler())
                    .observeOn(schedulerProvider.mainScheduler())
                    .doOnSubscribe { _filmsLiveData.postValue(FilmsState.Loading) }
                    .subscribeBy(
                        onSuccess = {
                            isShowError = true
                            filmsList.addAll(it.results)
                            _filmsLiveData.value = FilmsState.Success(filmsList)
                            nextPage = it.page + 1
                            if (lastPage != it.totalPages) {
                                lastPage++
                                _isLastPageLiveData.value = false
                            } else
                                _isLastPageLiveData.value = true
                        },
                        onError = {
                            if (isShowError){
                                _errorLiveData.value = it.message ?: DEFAULT_ERROR
                                isShowError = false
                            }
                        })
            )
        } else if (isShowNotConnectError) {
            isShowNotConnectError = false
            _errorLiveData.value = NOT_CONNECT
        }
    }

    override fun onClickFilm(position: Int) {
        _filmsLiveData.value = FilmsState.DescriptionFilm(filmsList[position].id)
    }

    companion object {
        private const val DEFAULT_ERROR = "Default error"
        private const val NOT_CONNECT = "No internet connection"
        private const val START_PAGE = 1
    }
}