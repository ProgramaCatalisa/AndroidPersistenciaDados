package br.com.zup.movieflix.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.zup.movieflix.domain.model.Movie

@Dao
interface MovieDAO {
    @Query("SELECT * from filmes ORDER BY titulo ASC")
     fun getMovieList(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertMovie(vararg movies: Movie)
}