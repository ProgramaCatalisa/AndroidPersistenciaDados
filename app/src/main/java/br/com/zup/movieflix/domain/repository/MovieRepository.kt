package br.com.zup.movieflix.domain.repository

import br.com.zup.movieflix.data.datasource.local.dao.MovieDAO
import br.com.zup.movieflix.domain.model.Movie

class MovieRepository(private val movieDAO: MovieDAO) {

   suspend fun getAllMovies(): List<Movie> = movieDAO.getAllMovies()

   suspend fun insertMovie(movie: Movie){
        movieDAO.insertMovie(movie)
    }
}