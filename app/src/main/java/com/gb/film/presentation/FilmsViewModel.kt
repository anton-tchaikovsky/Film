package com.gb.film.presentation

import androidx.lifecycle.LiveData
import com.gb.film.utility.SingleEventLiveData

interface FilmsViewModel {
    val filmsLiveData: LiveData<FilmsState>

    val errorLiveData: SingleEventLiveData<String>

    fun getFilms()

    fun onClickFilm(position: Int)
}