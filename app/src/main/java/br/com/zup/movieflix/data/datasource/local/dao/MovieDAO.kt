package br.com.zup.movieflix.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.zup.movieflix.domain.model.Movie

@Dao
interface MovieDAO {
    @Query("SELECT * FROM filmes ORDER BY titulo ASC")
    fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM filmes WHERE titulo =:titleMovie")
    fun getMovieTitle(titleMovie: String): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)
}