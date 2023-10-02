package com.gb.film.data.scheduler_provider

import io.reactivex.rxjava3.core.Scheduler

interface SchedulerProvider {

    fun mainScheduler(): Scheduler

    fun ioScheduler(): Scheduler

}