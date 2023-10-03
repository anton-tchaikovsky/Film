package com.gb.film.data.network_status

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface NetWorkStatus {
    fun isConnectSingle(): Single<Boolean>
    fun isConnectObservable(): Observable<Boolean>
}