package com.gb.film.presentation.description_film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.gb.film.FilmApp
import com.gb.film.data.retrofit.ApiFilms
import com.gb.film.databinding.FragmentDescriptionFilmBinding
import com.gb.film.domain.entity.description_film.DescriptionFilm
import javax.inject.Inject
import kotlin.properties.Delegates

private const val KEY_ID_FILM = "KeyIdFilm"

class DescriptionFilmFragment : Fragment() {

    private var _binding: FragmentDescriptionFilmBinding? = null
    private val binding get() = _binding!!

    private var idFilm: Int by Delegates.notNull()

    @Inject
    lateinit var descriptionFilmViewModelFactory: DescriptionFilmViewModelFactory

    private val descriptionFilmViewModel: DescriptionFilmViewModel by viewModels {
        descriptionFilmViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idFilm = it.getInt(KEY_ID_FILM)
        }
        FilmApp.instance.initDescriptionFilmScope().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        descriptionFilmViewModel.run {
            descriptionFilmLiveData.observe(viewLifecycleOwner){
                showDescriptionFilm(it)
            }
            errorLiveData.observe(viewLifecycleOwner){
                showError(it)
            }
            getDescriptionFilm(idFilm)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        FilmApp.instance.releaseDescriptionFilmScope()
        super.onDestroy()
    }

    private fun showDescriptionFilm(descriptionFilm: DescriptionFilm){
        binding.run {
            titleTextView.text = descriptionFilm.originalTitle
            overviewTextView.text = descriptionFilm.overview
            backdropImageView.load("${ApiFilms.IMAGE_URL}${ApiFilms.IMAGE_END_POINT}${descriptionFilm.backdropPath}")
        }
    }

    private fun showError(message: String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(idFilm: Int) =
            DescriptionFilmFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_ID_FILM, idFilm)
                }
            }
    }
}