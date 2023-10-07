package com.gb.film.presentation.films

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gb.film.FilmApp
import com.gb.film.R
import com.gb.film.databinding.ActivityFilmsBinding
import com.gb.film.presentation.description_film.DescriptionFilmFragment
import com.gb.film.presentation.films.films_list.FilmsListAdapter
import com.gb.film.presentation.films.films_list.PaginationScrollListener
import javax.inject.Inject

class FilmsActivity : AppCompatActivity() {
    @Inject
    lateinit var filmsViewModelFactory: FilmsViewModelFactory

    private val filmsViewModel: FilmsViewModel by viewModels {
        filmsViewModelFactory
    }

    private lateinit var binding: ActivityFilmsBinding

    private val filmsAdapter: FilmsListAdapter = FilmsListAdapter { filmsViewModel.onClickFilm(it) }

    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FilmApp.instance.appComponent.inject(this)
        initFilmsRecyclerView()
        filmsViewModel.apply {
            isLastPageLiveData.observe(this@FilmsActivity){
                isLastPage = it
            }
            filmsLiveData.observe(this@FilmsActivity){
                isLoading = false
                renderData(it)
            }
            errorLiveData.observe(this@FilmsActivity){
                isLoading = false
                showError(it)
            }
            getFilms()
        }
    }

    private fun initFilmsRecyclerView() {
        binding.filmsRecyclerView.apply {
            val filmsLayoutManager = LinearLayoutManager(this@FilmsActivity, RecyclerView.VERTICAL, false)
            layoutManager = filmsLayoutManager
            adapter = filmsAdapter
            addOnScrollListener(object : PaginationScrollListener(filmsLayoutManager){
                override fun isLastPage(): Boolean = isLastPage

                override fun isLoading(): Boolean = isLoading

                override fun loadMoreItems() {
                    filmsViewModel.getFilms()
                }
            })
        }
    }

    private fun renderData(filmsState: FilmsState){
        when(filmsState){
            FilmsState.Loading -> isLoading = true
            is FilmsState.Success -> {
                filmsAdapter.setFilmsList(filmsState.result)
            }
            is FilmsState.DescriptionFilm -> {
                openDescriptionFilmFragment(filmsState.idFilm)
            }
        }
    }

    private fun showError(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun openDescriptionFilmFragment(idFilm:Int){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, DescriptionFilmFragment.newInstance(idFilm))
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
}