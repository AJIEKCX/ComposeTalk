package ru.alexpanov.composetalk.ui.login.mvp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.alexpanov.composetalk.ui.login.widget.LoginContent
import ru.alexpanov.composetalk.ui.theme.ComposeTalkTheme

class LoginMvpComposeActivity : MvpAppCompatActivity(), LoginViewAdapter {
    override val state = mutableStateOf(LoginState())

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter(): LoginPresenter {
        return LoginPresenter()
    }

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
        var login by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        val scaffoldState = rememberScaffoldState()
        val onLoginChange: (String) -> Unit = remember { { login = it; presenter.setLogin(it) } }
        val onPasswordChange: (String) -> Unit = remember { { password = it; presenter.setPassword(it) } }

        state.value.message?.let {
            LaunchedEffect(scaffoldState.snackbarHostState) {
                scaffoldState.snackbarHostState.showSnackbar(it)
                hideMessage()
            }
        }

        LoginContent(
            scaffoldState = scaffoldState,
            login = login,
            password = password,
            signInButtonEnabled = state.value.isAuthEnabled,
            onLoginChange = onLoginChange,
            onPasswordChange = onPasswordChange,
            onSignInClick = presenter::authorize
        )
    }
}
