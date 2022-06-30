package br.com.zup.movieflix.ui.moviedetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.movieflix.MOVIE_KEY
import br.com.zup.movieflix.R
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
        (activity as HomeActivity).supportActionBar?.title = getString(R.string.movie_title_detail)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            NavHostFragment.findNavController(this).navigate(
                R.id.action_movieDetailFragment_to_movieListFragment
            )
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}