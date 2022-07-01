package br.com.zup.movieflix.data.datasource.local.dao

import androidx.room.TypeConverter
import br.com.zup.movieflix.domain.model.Director
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromDirector(value: String?): Director? {
        return Gson().fromJson(value, Director::class.java)
    }

    @TypeConverter
    fun directorToString(director: Director?): String {
        return Gson().toJson(director)
    }
}