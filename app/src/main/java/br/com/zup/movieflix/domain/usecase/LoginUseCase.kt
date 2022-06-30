package br.com.zup.movieflix.domain.usecase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zup.movieflix.SAVE_USER_PASS_FLAG_KEY
import br.com.zup.movieflix.domain.model.User
import br.com.zup.movieflix.ui.viewstate.ViewState

class LoginUseCase(private val context: Context) {
    private val SHARED_PREF = "movieflix"
    private val SHARED_PREF_KEY_EMAIL = "email"
    private val SHARED_PREF_KEY_PASSWORD = "password"
    private val pref = context.getSharedPreferences(SHARED_PREF, 0)
    private val edit = pref.edit()

    fun login(user: User, flagSaveData: Boolean): LiveData<ViewState<User>> {

        val response = MutableLiveData<ViewState<User>>()

        if (user.email == "catalisa@zup.com.br" && user.password == "123456") {

            edit.putBoolean(SAVE_USER_PASS_FLAG_KEY, flagSaveData)

            if (flagSaveData) {
                saveDataUser(user)
            } else {
                removeDataUser()
            }

            response.value = ViewState.Success(user)
        } else {
            response.value = ViewState.Error(Exception("Usuário ou senha invalido"))
        }
        return response
    }

    fun getUserLogged(): LiveData<ViewState<User>> {

        val response = MutableLiveData<ViewState<User>>()

        val email = pref.getString(SHARED_PREF_KEY_EMAIL, "") ?: ""
        val password = pref.getString(SHARED_PREF_KEY_PASSWORD, "") ?: ""

        val user = User("Catalisa", email, password)
        response.value = ViewState.Success(user)

        return response
    }

    private fun saveDataUser(user: User) {
        edit.putString(SHARED_PREF_KEY_EMAIL, user.email)
        edit.putString(SHARED_PREF_KEY_PASSWORD, user.password)
        edit.apply()
    }

    private fun removeDataUser() {
        edit.remove(SHARED_PREF_KEY_EMAIL)
        edit.remove(SHARED_PREF_KEY_PASSWORD)
        edit.apply()
    }
}