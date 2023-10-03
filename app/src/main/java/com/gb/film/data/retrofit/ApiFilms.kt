package com.gb.film.data.retrofit

import com.gb.film.BuildConfig
import com.gb.film.domain.entity.description_film.DescriptionFilm
import com.gb.film.domain.entity.films.Films
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiFilms {
    @GET (FILMS_END_POINT)
    fun loadFilms(
        @Query(API_KEY) apiKey: String = BuildConfig.api_key,
        @Query (PAGE) page: Int
    ): Single<Films>

    @GET ("$DESCRIPTION_FILMS_END_POINT/{idFilm}")
    fun loadDescriptionFilm(
        @Path("idFilm") idFilm: Int,
        @Query(API_KEY) apiKey: String = BuildConfig.api_key
    ): Single<DescriptionFilm>

    companion object{
        private const val FILMS_END_POINT = "/3/discover/movie"
        private const val DESCRIPTION_FILMS_END_POINT = "/3/movie"
        private const val API_KEY = "api_key"
        private const val PAGE = "page"
        const val BASE_URL = "https://api.themoviedb.org"
        const val IMAGE_URL = "https://image.tmdb.org/"
        const val IMAGE_END_POINT = "/t/p/w500"
        const val MAX_FILMS_RATING = 10
    }
}