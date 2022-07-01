package br.com.zup.movieflix.ui.movieadd.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.movieflix.domain.model.Movie
import br.com.zup.movieflix.domain.usecase.MovieUseCase
import br.com.zup.movieflix.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieAddViewModel(application: Application) : AndroidViewModel(application) {
    private val movieUseCase = MovieUseCase(application)
    val movieAddState = MutableLiveData<ViewState<Movie>>()

    fun insertMovie(movie: Movie) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    movieUseCase.insertMovie(movie)
                }
                movieAddState.value = response
            } catch (ex: Exception) {
                movieAddState.value =
                    ViewState.Error(Throwable("Tivemos um problema! Tente mais tarde."))
            }
        }
    }
}