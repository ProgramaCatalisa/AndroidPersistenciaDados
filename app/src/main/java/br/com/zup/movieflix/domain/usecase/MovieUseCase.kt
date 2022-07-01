package br.com.zup.movieflix.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zup.movieflix.*
import br.com.zup.movieflix.domain.model.Director
import br.com.zup.movieflix.domain.model.Movie
import br.com.zup.movieflix.ui.viewstate.ViewState

class MovieUseCase {

    fun getMovieList(): LiveData<ViewState<List<Movie>>> {
        val response = MutableLiveData<ViewState<List<Movie>>>()
        val listMovie = createMovieList()

        if (listMovie.isNotEmpty()) {
            response.value = ViewState.Success(listMovie)
        } else {
            response.value =
                ViewState.Error(Exception("Não foi possível carregar a lista de filmes!"))
        }
        return response
    }

    private fun createMovieList(): List<Movie> {
        val listMovie = mutableListOf<Movie>()
        listMovie.add(
            Movie(
                PULP_FICTION,
                PULP_FICTION_SINOPSE,
                R.drawable.pulpfiction,
                Director(
                    TARANTINO,
                    TARANTINO_INFO
                )
            )
        )
        listMovie.add(
            Movie(
                TAXI_DRIVER,
                TAXI_DRIVER_SINOPSE,
                R.drawable.taxidriver,
                Director(
                    MARTIN_SCORSESE,
                    MARTIN_SCORSESE_INFO
                )
            )
        )
        listMovie.add(
            Movie(
                DJANGO,
                DJANGO_SINOPSE,
                R.drawable.jango,
                Director(
                    TARANTINO,
                    TARANTINO_INFO
                )
            )
        )
        listMovie.add(
            Movie(
                GOODFELLAS,
                GOODFELLAS_SINOPSE,
                R.drawable.bonscompanheiros,
                Director(
                    MARTIN_SCORSESE,
                    MARTIN_SCORSESE_INFO
                )
            )
        )
        listMovie.add(
            Movie(
                RESEVOIR_DOGS,
                RESEVOIR_DOGS_SINOPSE,
                R.drawable.reservoirdogs,
                Director(
                    TARANTINO,
                    TARANTINO_INFO
                )
            )
        )
        listMovie.add(
            Movie(
                WOLF_WALLSTREET,
                WOLF_WALLSTREET_SINOPSE,
                R.drawable.wolfwallstreet,
                Director(
                    MARTIN_SCORSESE,
                    MARTIN_SCORSESE_INFO
                )
            )
        )
        return listMovie
    }
}