package ru.alexpanov.composetalk.ui.login.mvvm.binding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginComposeViewModel : ViewModel() {
    private val _login = MutableStateFlow("")
    val login = _login.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    val isAuthEnabled: StateFlow<Boolean> = login.combine(password) { _, _ ->
        validateFields()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun setLogin(value: String) {
        _login.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    fun authorize() {
        if (validateFields().not()) return

        viewModelScope.launch {
            _message.emit("Success")
        }
    }

    private fun validateFields(): Boolean {
        return login.value.isNotBlank() && password.value.isNotBlank()
    }
}