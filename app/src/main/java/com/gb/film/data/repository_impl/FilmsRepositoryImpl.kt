package com.gb.film.data.repository_impl

import com.gb.film.data.data_source.RemoteDataSource
import com.gb.film.domain.entity.Films
import com.gb.film.domain.repository.FilmsRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource): FilmsRepository {
    override fun getFilms(page: Int): Single<Films> =
        remoteDataSource.getFilms(page)
}