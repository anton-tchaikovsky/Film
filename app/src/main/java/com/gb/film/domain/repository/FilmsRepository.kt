package com.gb.film.domain.repository

import com.gb.film.domain.entity.description_film.DescriptionFilm
import com.gb.film.domain.entity.films.Films
import io.reactivex.rxjava3.core.Single

interface FilmsRepository {
    var isConnect: Boolean

    fun getFilms(page: Int): Single<Films>

    fun getDescriptionFilm(idFilm: Int): Single<DescriptionFilm>
}