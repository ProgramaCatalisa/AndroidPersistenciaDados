package br.com.zup.movieflix.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "filmes")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cod_filme")
    var codMovie: Int = 1,

    @ColumnInfo(name = "titulo")
    var title: String,

    @ColumnInfo(name = "sinopse")
    var sinopse: String,

    @ColumnInfo(name = "diretor")
    var director: Director,

    var image: Int = 0,

    var isFavorite: Boolean = false
) : Parcelable