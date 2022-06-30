package br.com.zup.movieflix.ui.movielist.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.movieflix.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}