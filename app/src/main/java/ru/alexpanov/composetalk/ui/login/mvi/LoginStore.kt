package ru.alexpanov.composetalk.ui.login.mvi

import ru.alexpanov.composetalk.ui.login.mvi.LoginContract.Event
import ru.alexpanov.composetalk.ui.login.mvi.LoginContract.SideEffect
import ru.alexpanov.composetalk.ui.login.mvi.LoginContract.State

class LoginStore : BaseStore<Event, State, SideEffect>() {
    override fun createInitialState(): State = State()

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.EmailChanged -> {
                setState { copy(email = event.email) }
            }
            is Event.PasswordChanged -> {
                setState { copy(password = event.password) }
            }
            Event.SignInClicked -> authorize()
        }
    }

    private fun authorize() {
        if (currentState.isSignInEnabled.not()) return

        setSideEffect { SideEffect.ShowMessage("Success") }
    }
}
