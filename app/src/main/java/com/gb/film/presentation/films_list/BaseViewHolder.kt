package com.gb.film.presentation.films_list

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.gb.film.domain.entity.Result

abstract class BaseViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(result: Result)
}