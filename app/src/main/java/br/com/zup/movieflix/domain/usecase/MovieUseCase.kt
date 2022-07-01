package br.com.zup.movieflix.domain.usecase

import android.app.Application
import br.com.zup.movieflix.data.datasource.local.MovieRoomDatabase
import br.com.zup.movieflix.domain.model.Movie
import br.com.zup.movieflix.domain.repository.MovieRepository
import br.com.zup.movieflix.ui.viewstate.ViewState

class MovieUseCase(application: Application) {
    private val movieDao = MovieRoomDatabase.getDatabase(application).movieDAO()
    private val movieRepository = MovieRepository(movieDao)

    suspend fun getAllMovies(): ViewState<List<Movie>> {
        return try {
            val listMovie = movieRepository.getAllMovies()
            ViewState.Success(listMovie)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível carregar a lista de filmes!"))
        }
    }

    suspend fun insertMovie(movie: Movie): ViewState<Movie> {
        return try {
            movieRepository.insertMovie(movie)
            ViewState.Success(movie)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível inserir o filme!"))
        }
    }
}