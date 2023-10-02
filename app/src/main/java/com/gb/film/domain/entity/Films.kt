package com.gb.film.domain.entity

import com.google.gson.annotations.SerializedName

data class Films(
    val page: Int,
    val results: List<Result>,
    @field:SerializedName("total_pages")val totalPages: Int,
    @field:SerializedName("total_results")val totalResults: Int
)