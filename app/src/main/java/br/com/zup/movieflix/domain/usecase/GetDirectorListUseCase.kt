package br.com.zup.movieflix.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zup.movieflix.MARTIN_SCORSESE
import br.com.zup.movieflix.MARTIN_SCORSESE_INFO
import br.com.zup.movieflix.TARANTINO
import br.com.zup.movieflix.TARANTINO_INFO
import br.com.zup.movieflix.domain.model.Director
import br.com.zup.movieflix.ui.viewstate.ViewState

class GetDirectorListUseCase {

    fun getDirectorList(): LiveData<ViewState<List<Director>>> {
        val response = MutableLiveData<ViewState<List<Director>>>()
        val listDirector = createDirectorList()

        if (listDirector.isNotEmpty()) {
            response.value = ViewState.Success(listDirector)
        } else {
            response.value =
                ViewState.Error(Exception("Não foi possível carregar a lista de diretores!"))
        }
        return response
    }

    private fun createDirectorList() = mutableListOf(
        Director(
            TARANTINO,
            TARANTINO_INFO
        ),
        Director(
            MARTIN_SCORSESE,
            MARTIN_SCORSESE_INFO
        )
    )
}

