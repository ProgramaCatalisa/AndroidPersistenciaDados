package br.com.zup.movieflix.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Director(
    var name: String,
    var info: String
) : Parcelable