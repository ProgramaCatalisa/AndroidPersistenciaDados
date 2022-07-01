package br.com.zup.movieflix.ui.movielist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.movieflix.domain.model.Movie
import br.com.zup.movieflix.domain.usecase.MovieUseCase
import br.com.zup.movieflix.ui.viewstate.ViewState

class MovieViewModel : ViewModel() {
    private val movieListUseCase = MovieUseCase()
    val movieListState = MutableLiveData<ViewState<List<Movie>>>()

    fun getMovieList() {
       try {
           movieListState.value = movieListUseCase.getMovieList().value
       }catch (ex: Exception){
           movieListState.value = ViewState.Error(Throwable("Tivemos um problema! Tente mais tarde."))
       }
    }
}