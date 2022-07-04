package br.com.zup.movieflix.data.datasource.local

import androidx.room.TypeConverter
import br.com.zup.movieflix.domain.model.Director
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun converterFromDirector(value: String): Director?{
        return Gson().fromJson(value, Director::class.java)
    }

    @TypeConverter
    fun converterToJson(director: Director): String{
        return Gson().toJson(director)
    }
}