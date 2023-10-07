package com.gb.film.domain.entity.description_film

import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    val id: Int,
    @field:SerializedName("logo_path") val logoPath: String,
    val name: String,
    @field:SerializedName("origin_country") val originCountry: String
)