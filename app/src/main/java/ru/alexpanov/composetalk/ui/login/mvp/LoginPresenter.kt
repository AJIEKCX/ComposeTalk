package ru.alexpanov.composetalk.ui.login.mvp

import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {
    private var login: String = ""
    private var password: String = ""

    fun setLogin(value: String) {
        login = value
        checkAuthEnabled()
    }

    fun setPassword(value: String) {
        password = value
        checkAuthEnabled()
    }

    fun authorize() {
        if (validateFields().not()) return

        viewState.showMessage("Success")
    }

    private fun checkAuthEnabled() {
        viewState.setAuthEnabled(validateFields())
    }

    private fun validateFields() = login.isNotBlank() && password.isNotBlank()
}
