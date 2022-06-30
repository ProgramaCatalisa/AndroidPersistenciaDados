package br.com.zup.movieflix.domain.model

data class User(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var confirmationPassword: String = ""
)