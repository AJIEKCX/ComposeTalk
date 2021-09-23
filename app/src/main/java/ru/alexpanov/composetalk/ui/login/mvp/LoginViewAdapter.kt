package ru.alexpanov.composetalk.ui.login.mvp

import androidx.compose.runtime.MutableState

interface LoginViewAdapter : LoginView {
    val state: MutableState<LoginState>

    override fun setAuthEnabled(value: Boolean) {
        state.value = state.value.copy(isAuthEnabled = value)
    }

    override fun showMessage(value: String) {
        state.value = state.value.copy(message = value)
    }

    fun hideMessage() {
        state.value = state.value.copy(message = null)
    }
}

data class LoginState(
    val isAuthEnabled: Boolean = false,
    val message: String? = null
)