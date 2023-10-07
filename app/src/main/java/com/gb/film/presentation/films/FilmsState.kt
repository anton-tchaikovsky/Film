package com.gb.film.presentation.films

import com.gb.film.domain.entity.films.Result

sealed interface FilmsState {
    data class Success(val result: List<Result>) : FilmsState

    data class DescriptionFilm(val idFilm: Int) : FilmsState

    object Loading : FilmsState
}