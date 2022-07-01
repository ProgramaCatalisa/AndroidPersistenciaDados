package br.com.zup.movieflix.ui.moviedetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.zup.movieflix.MOVIE_KEY
import br.com.zup.movieflix.databinding.FragmentMovieDetailBinding
import br.com.zup.movieflix.domain.model.Movie
import br.com.zup.movieflix.ui.home.view.HomeActivity

class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPassedData()
    }

    private fun getPassedData() {
        val movie = arguments?.getParcelable<Movie>(MOVIE_KEY)
        movie?.let {
            binding.imageView.setImageResource(it.image)
            binding.tvMovieTitle.text = it.title
            binding.tvMovieSinopse.text = it.sinopse
            binding.tvDirectorName.text = it.director.name
            binding.tvDirectorInfo.text = it.director.info
            (activity as HomeActivity).supportActionBar?.title = it.title
        }
    }
}