package com.gb.film.data.data_source

import com.gb.film.data.retrofit.ApiFilms
import com.gb.film.domain.entity.Films
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiFilms: ApiFilms): RemoteDataSource {
    override fun getFilms(page: Int): Single<Films> =
        apiFilms.loadFilms(page = page)
}