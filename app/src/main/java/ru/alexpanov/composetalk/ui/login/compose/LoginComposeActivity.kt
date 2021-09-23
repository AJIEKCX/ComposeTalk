package ru.alexpanov.composetalk.ui.login.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.launch
import ru.alexpanov.composetalk.ui.login.widget.LoginContent

class LoginComposeActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginScreen()
        }
    }

    @Composable
    fun LoginScreen() {
        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        val signInEnabled by remember(email, password) {
            derivedStateOf { email.isNotBlank() && password.isNotBlank() }
        }
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()

        val onSignInClick = remember { {
            if (signInEnabled) {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                    scaffoldState.snackbarHostState.showSnackbar("Success")
                }
            }
        } }

        LoginContent(
            scaffoldState = scaffoldState,
            login = email,
            password = password,
            signInButtonEnabled = signInEnabled,
            onLoginChange = { email = it },
            onPasswordChange = { password = it },
            onSignInClick = onSignInClick
        )
    }
}