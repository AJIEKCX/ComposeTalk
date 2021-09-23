package ru.alexpanov.composetalk.ui.login.mvvm.binding

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

class LoginDataBindingComposeActivity : FragmentActivity() {
    private val viewModel by viewModels<LoginComposeViewModel>()

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
        val login by viewModel.login.collectAsState()
        val password by viewModel.password.collectAsState()
        val isAuthEnabled by viewModel.isAuthEnabled.collectAsState(false)
        val scaffoldState = rememberScaffoldState()

        LaunchedEffect(scaffoldState.snackbarHostState) {
            viewModel.message.onEach {
                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                scaffoldState.snackbarHostState.showSnackbar(it)
            }.launchIn(this)
        }

        LoginContent(
            scaffoldState = scaffoldState,
            login = login,
            password = password,
            signInButtonEnabled = isAuthEnabled,
            onLoginChange = viewModel::setLogin,
            onPasswordChange = viewModel::setPassword,
            onSignInClick = viewModel::authorize
        )
    }
}