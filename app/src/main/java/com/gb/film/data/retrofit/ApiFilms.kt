package com.gb.film.data.retrofit

import com.gb.film.BuildConfig
import com.gb.film.domain.entity.Films
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFilms {
    @GET (END_POINT)
    fun loadFilms(
        @Query(API_KEY) apiKey: String = BuildConfig.api_key,
        @Query (PAGE) page: Int
    ): Single<Films>

    companion object{
        private const val END_POINT = "/3/discover/movie"
        private const val API_KEY = "api_key"
        private const val PAGE = "page"
        const val BASE_URL = "https://api.themoviedb.org"
        const val IMAGE_URL = "https://image.tmdb.org/"
        const val IMAGE_END_POINT = "/t/p/w500"
        const val MAX_FILMS_RATING = 10
    }
}