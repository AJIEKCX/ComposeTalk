package ru.alexpanov.composetalk.ui.login.mvvm

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.reactivex.Observable
import kotlinx.coroutines.launch
import ru.alexpanov.composetalk.ui.login.widget.LoginContent
import ru.alexpanov.composetalk.ui.theme.ComposeTalkTheme

class LoginMvvmComposeActivity : FragmentActivity() {
    private val viewModel by viewModels<LoginViewModel>()

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
        val login by viewModel.login.observeAsState("")
        val password by viewModel.password.observeAsState("")
        val isAuthEnabled by viewModel.isAuthEnabled.observeAsState(false)
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        viewModel.message.observeEvent {
            scope.launch {
                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                scaffoldState.snackbarHostState.showSnackbar(it)
            }
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

    @SuppressLint("ComposableNaming")
    @Composable
    fun <R, T : R> LiveData<T>.observeEvent(callback: (T) -> Unit) {
        val lifecycleOwner = LocalLifecycleOwner.current

        DisposableEffect(this, lifecycleOwner) {
            val observer = Observer<T> { callback(it) }
            observe(lifecycleOwner, observer)
            onDispose { removeObserver(observer) }
        }
    }
}