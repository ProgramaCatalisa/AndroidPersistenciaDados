package br.com.zup.movieflix.ui.moviedetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.movieflix.domain.model.Director
import br.com.zup.movieflix.domain.usecase.GetDirectorListUseCase
import br.com.zup.movieflix.ui.viewstate.ViewState

class MovieDetailViewModel : ViewModel() {
    private val directorListUseCase = GetDirectorListUseCase()
    val directorListState = MutableLiveData<ViewState<List<Director>>>()

    fun getMovieList() {
        try {
            directorListState.value = directorListUseCase.getDirectorList().value
        } catch (ex: Exception) {
            directorListState.value =
                ViewState.Error(Throwable("Tivemos um problema! Tente mais tarde."))
        }
    }
}