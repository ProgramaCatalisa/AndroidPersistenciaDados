package br.com.zup.movieflix.data.datasource.local

import android.content.Context
import androidx.room.*
import br.com.zup.movieflix.data.datasource.local.dao.Converters
import br.com.zup.movieflix.data.datasource.local.dao.MovieDAO
import br.com.zup.movieflix.domain.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        fun getDatabase(context: Context): MovieRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    "movie_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}