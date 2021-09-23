package ru.alexpanov.composetalk.ui.login.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable

class LoginViewModel : ViewModel() {
    private val _login = MutableLiveData<String>()
    val login: LiveData<String> get() = _login

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    val message = SingleLiveData<String>()

    val isAuthEnabled = MediatorLiveData<Boolean>().apply {
        addSource(login) { checkAuthEnabled() }
        addSource(password) { checkAuthEnabled() }
    }

    fun setLogin(value: String) {
        _login.value = value
    }

    fun setPassword(value: String) {
       _password.value = value
    }

    fun authorize() {
        if (validateFields().not()) return

        message.value = "Success"
    }

    private fun checkAuthEnabled() {
        isAuthEnabled.value = validateFields()
    }

    private fun validateFields(): Boolean {
        return (login.value.isNullOrBlank() || password.value.isNullOrBlank()).not()
    }
}
