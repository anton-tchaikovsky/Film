package com.gb.film.presentation.films

import androidx.lifecycle.LiveData
import com.gb.film.utility.SingleEventLiveData

interface FilmsViewModel {
    val filmsLiveData: LiveData<FilmsState>

    val isLastPageLiveData: LiveData<Boolean>

    val errorLiveData: SingleEventLiveData<String>

    fun getFilms()

    fun onClickFilm(position: Int)
}