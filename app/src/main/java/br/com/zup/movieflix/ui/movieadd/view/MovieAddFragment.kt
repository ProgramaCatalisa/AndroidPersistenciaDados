package br.com.zup.movieflix.ui.movieadd.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.zup.movieflix.databinding.FragmentMovieAddBinding
import br.com.zup.movieflix.domain.model.Director
import br.com.zup.movieflix.domain.model.Movie
import br.com.zup.movieflix.ui.home.view.HomeActivity
import br.com.zup.movieflix.ui.movieadd.viewmodel.MovieAddViewModel
import br.com.zup.movieflix.ui.moviedetail.viewmodel.MovieDetailViewModel
import br.com.zup.movieflix.ui.movielist.viewmodel.MovieViewModel
import br.com.zup.movieflix.ui.viewstate.ViewState

class MovieAddFragment : Fragment() {
    private lateinit var binding: FragmentMovieAddBinding
    private val viewModel: MovieAddViewModel by lazy {
        ViewModelProvider(this)[MovieAddViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bvSaveMovie.setOnClickListener {
            viewModel.insertMovie(
                Movie(
                    title = binding.etMovieTitleAdd.text.toString(),
                    sinopse = binding.etMovieSinopseAdd.text.toString(),
                    director = Director(
                        name = binding.etMovieDirectorNameAdd.text.toString(),
                        info = binding.etMovieDirectorInfoAdd.text.toString()
                    )
                )
            )
        }

        initObserver()
    }

    private fun initObserver() {
        viewModel.movieAddState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    Toast.makeText(
                        context,
                        "Filme cadastrado com sucesso!",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }
}