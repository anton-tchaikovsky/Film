package com.gb.film.data.data_source

import com.gb.film.domain.entity.Films
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource {
    fun getFilms(page: Int): Single<Films>
}