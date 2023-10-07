package com.gb.film.data.repository_impl

import android.annotation.SuppressLint
import com.gb.film.data.data_source.RemoteDataSource
import com.gb.film.data.network_status.NetWorkStatus
import com.gb.film.data.scheduler_provider.SchedulerProvider
import com.gb.film.domain.entity.description_film.DescriptionFilm
import com.gb.film.domain.entity.films.Films
import com.gb.film.domain.repository.FilmsRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject


@SuppressLint("CheckResult")
class FilmsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    schedulerProvider: SchedulerProvider,
    netWorkStatus: NetWorkStatus
) : FilmsRepository {

    override var isConnect: Boolean = false

    init {
        netWorkStatus.isConnectObservable()
            .subscribeOn(schedulerProvider.ioScheduler())
            .subscribeBy {
                isConnect = it
            }
    }

    override fun getFilms(page: Int): Single<Films> =
            remoteDataSource.getFilms(page)

    override fun getDescriptionFilm(idFilm: Int): Single<DescriptionFilm> =
            remoteDataSource.getDescriptionFilm(idFilm)
}