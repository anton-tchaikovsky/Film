package com.gb.film.domain.entity.description_film

import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @field:SerializedName("english_name") val englishName: String,
    @field:SerializedName("iso_639_1") val Iso: String,
    val name: String
)