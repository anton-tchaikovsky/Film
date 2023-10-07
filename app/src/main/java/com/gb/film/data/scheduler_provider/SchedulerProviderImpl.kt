package com.gb.film.data.scheduler_provider

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SchedulerProviderImpl @Inject constructor(): SchedulerProvider {
    override fun mainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    override fun ioScheduler(): Scheduler = Schedulers.io()
}