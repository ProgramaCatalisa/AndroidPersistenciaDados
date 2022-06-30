package br.com.zup.movieflix.ui.movielist.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.movieflix.MOVIE_KEY
import br.com.zup.movieflix.databinding.ActivityMovieBinding
import br.com.zup.movieflix.domain.model.Movie
import br.com.zup.movieflix.ui.moviedetail.view.MovieDetailActivity
import br.com.zup.movieflix.ui.movielist.viewmodel.MovieViewModel
import br.com.zup.movieflix.ui.viewstate.ViewState

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class.java]
    }

    private val adapter: MovieAdapter by lazy {
        MovieAdapter(arrayListOf(), this::goToMovieDetail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getMovieList()
        initObserver()
        setUpRvMovieList()
    }

    private fun initObserver() {
        viewModel.movieListState.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    adapter.updateMovieList(it.data)
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        this,
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
        binding.rvMovieList.layoutManager = LinearLayoutManager(this)
    }


    private fun goToMovieDetail(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java).apply {
            putExtra(MOVIE_KEY, movie)
        }
        startActivity(intent)
    }
}