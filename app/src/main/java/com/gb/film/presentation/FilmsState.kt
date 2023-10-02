package com.gb.film.presentation

import com.gb.film.domain.entity.Result

sealed interface FilmsState {
    data class Success(val result: List<Result>) : FilmsState
    object Loading : FilmsState
}