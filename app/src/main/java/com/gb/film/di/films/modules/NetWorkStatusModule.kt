package com.gb.film.di.films.modules

import com.gb.film.data.network_status.NetWorkStatus
import com.gb.film.data.network_status.NetWorkStatusImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NetWorkStatusModule {
    @Singleton
    @Binds
    fun getNetWorkStatus(netWorkStatusImpl: NetWorkStatusImpl): NetWorkStatus
}