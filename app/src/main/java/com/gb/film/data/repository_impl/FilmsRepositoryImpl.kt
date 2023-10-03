package com.gb.film.data.repository_impl

import com.gb.film.data.data_source.RemoteDataSource
import com.gb.film.domain.entity.description_film.DescriptionFilm
import com.gb.film.domain.entity.films.Films
import com.gb.film.domain.repository.FilmsRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource): FilmsRepository {
    override fun getFilms(page: Int): Single<Films> =
        remoteDataSource.getFilms(page)

    override fun getDescriptionFilm(idFilm: Int): Single<DescriptionFilm> =
        remoteDataSource.getDescriptionFilm(idFilm)
}