package br.com.zup.movieflix.ui.viewstate

import br.com.zup.movieflix.domain.model.User

sealed class ViewState<out T> {
    data class Success<T>(val data: T) : ViewState<T>()
    data class Error(val throwable: Throwable) : ViewState<Nothing>()
}
