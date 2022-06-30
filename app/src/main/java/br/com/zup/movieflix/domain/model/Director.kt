package br.com.zup.movieflix.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Director(
    var name: String,
    var info: String
) : Parcelable