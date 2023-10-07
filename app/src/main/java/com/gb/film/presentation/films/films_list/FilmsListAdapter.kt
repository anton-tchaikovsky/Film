package com.gb.film.presentation.films.films_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.film.databinding.ItemFilmBinding
import com.gb.film.domain.entity.films.Result

class FilmsListAdapter(
    private val itemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<BaseViewHolder>() {
    private var filmsList: List<Result> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        FilmViewHolder(
            ItemFilmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClickListener
        )

    override fun getItemCount(): Int =
        filmsList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) =
        holder.bind(filmsList[position])

    fun setFilmsList(filmsList: List<Result>) {
        val oldSize = this.filmsList.size
        this.filmsList = filmsList
        notifyItemRangeChanged(oldSize, this.filmsList.size)
    }
}