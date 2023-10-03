package com.gb.film.presentation.films.films_list

import coil.load
import com.gb.film.data.retrofit.ApiFilms
import com.gb.film.databinding.ItemFilmBinding
import com.gb.film.domain.entity.films.Result

class FilmViewHolder(
    private val binding: ItemFilmBinding,
    private val itemClickListener: (Int) -> Unit
) : BaseViewHolder(binding) {
    override fun bind(result: Result) {
        binding.apply {
            titleTextView.text = result.title
            filmRatingBar.let {
                it.numStars = NUM_STARS
                it.rating =
                    ((result.voteAverage) / (ApiFilms.MAX_FILMS_RATING / NUM_STARS)).toFloat()
            }
            posterImageView.load("${ApiFilms.IMAGE_URL}${ApiFilms.IMAGE_END_POINT}${result.posterPath}")
        }
        itemView.setOnClickListener { itemClickListener(adapterPosition) }
    }

    companion object {
        private const val NUM_STARS = 5
    }
}