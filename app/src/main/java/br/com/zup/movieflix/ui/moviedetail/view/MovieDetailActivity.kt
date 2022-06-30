package br.com.zup.movieflix.ui.moviedetail.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.movieflix.MOVIE_KEY
import br.com.zup.movieflix.databinding.ActivityMovieDetailBinding
import br.com.zup.movieflix.domain.model.Movie

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPassedData()
    }

    private fun getPassedData() {
        val movie = intent.getParcelableExtra<Movie>(MOVIE_KEY)
        movie?.let {
            binding.imageView.setImageResource(it.image)
            binding.tvMovieTitle.text = it.title
            binding.tvMovieSinopse.text = it.sinopse
            binding.tvDirectorName.text = it.director.name
            binding.tvDirectorInfo.text = it.director.info
        }
    }
}