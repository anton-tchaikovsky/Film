package com.gb.film.domain.repository

import com.gb.film.domain.entity.Films
import io.reactivex.rxjava3.core.Single

interface FilmsRepository {
    fun getFilms(page: Int): Single<Films>
}