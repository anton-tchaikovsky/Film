package com.gb.film.presentation.description_film

import androidx.lifecycle.LiveData
import com.gb.film.domain.entity.description_film.DescriptionFilm
import com.gb.film.utility.SingleEventLiveData

interface DescriptionFilmViewModel {
    val descriptionFilmLiveData: LiveData<DescriptionFilm>

    val errorLiveData: SingleEventLiveData<String>

    fun getDescriptionFilm(idFilm: Int)
}