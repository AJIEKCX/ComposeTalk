package ru.alexpanov.composetalk.ui.login.mvi

class LoginContract {
    data class State(
        val email: String = "",
        val password: String = "",
    ) : UiState {
        val isSignInEnabled: Boolean
            get() = email.isNotBlank() && password.isNotBlank()
    }

    sealed class Event : UiEvent {
        data class EmailChanged(val email: String) : Event()
        data class PasswordChanged(val password: String) : Event()
        object SignInClicked : Event()
    }

    sealed class SideEffect : UiSideEffect {
        data class ShowMessage(val message: String) : SideEffect()
    }
}


fun LoginStore.onLoginChange(value: String) {
    setEvent(LoginContract.Event.EmailChanged(value))
}
fun LoginStore.onPasswordChange(value: String) {
    setEvent(LoginContract.Event.PasswordChanged(value))
}
fun LoginStore.onSignInClick() {
    setEvent(LoginContract.Event.SignInClicked)
}
