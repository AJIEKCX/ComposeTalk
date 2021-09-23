package ru.alexpanov.composetalk.ui.login.mvi

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.alexpanov.composetalk.ui.login.widget.LoginContent
import ru.alexpanov.composetalk.ui.theme.ComposeTalkTheme
import ru.alexpanov.composetalk.ui.login.mvi.LoginContract.SideEffect
import ru.alexpanov.composetalk.ui.login.mvi.LoginContract.Event

class LoginMviComposeActivity : FragmentActivity() {
    private val store by viewModels<LoginStore>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTalkTheme {
                LoginScreen()
            }
        }
    }

    @Composable
    fun LoginScreen() {
        val state by store.uiState.collectAsState()
        val scaffoldState = rememberScaffoldState()

        LaunchedEffect(scaffoldState.snackbarHostState) {
            store.sideEffect.onEach { sideEffect ->
                when (sideEffect) {
                    is SideEffect.ShowMessage -> {
                        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                        scaffoldState.snackbarHostState.showSnackbar(sideEffect.message)
                    }
                }
            }.launchIn(this)
        }

        LoginContent(
            scaffoldState = scaffoldState,
            login = state.email,
            password = state.password,
            signInButtonEnabled = state.isSignInEnabled,
            onLoginChange = store::onLoginChange,
            onPasswordChange = store::onPasswordChange,
            onSignInClick = store::onSignInClick
        )
    }
}