package br.com.zup.movieflix.ui.movielist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.movieflix.MOVIE_KEY
import br.com.zup.movieflix.databinding.FragmentMovieListBinding
import br.com.zup.movieflix.domain.model.Movie
import br.com.zup.movieflix.ui.moviedetail.view.MovieDetailActivity
import br.com.zup.movieflix.ui.movielist.view.MovieAdapter
import br.com.zup.movieflix.ui.movielist.viewmodel.MovieViewModel
import br.com.zup.movieflix.ui.viewstate.ViewState


class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieListBinding

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class.java]
    }

    private val adapter: MovieAdapter by lazy {
        MovieAdapter(arrayListOf(), this::goToMovieDetail)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieList()
        initObserver()
        setUpRvMovieList()
    }

    private fun initObserver() {
        viewModel.movieListState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    adapter.updateMovieList(it.data)
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

    private fun setUpRvMovieList() {
        binding.rvMovieList.adapter = adapter
        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
    }

    private fun goToMovieDetail(movie: Movie) {
        val intent = Intent(context, MovieDetailActivity::class.java).apply {
            putExtra(MOVIE_KEY, movie)
        }
        startActivity(intent)
    }
}