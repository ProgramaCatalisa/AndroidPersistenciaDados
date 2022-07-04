package br.com.zup.movieflix.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity(tableName = "filmes")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cod_filme")
    var codMovie: Long = 1,

    @ColumnInfo(name = "titulo")
    var title: String,

    @ColumnInfo(name = "sinopse")
    var sinopse: String,

    var image: Int = 0,

    @ColumnInfo(name = "diretores")
    var director: Director,

    var isFavorite: Boolean = false
) : Parcelable