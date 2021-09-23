package ru.alexpanov.composetalk.ui.login.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.alexpanov.composetalk.R

@Composable
fun LoginContent(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    login: String,
    password: String,
    signInButtonEnabled: Boolean,
    onLoginChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = remember { FocusRequester() }
    val onDone = remember { { focusManager.clearFocus(); onSignInClick() } }
    val onNext = remember { { passwordFocusRequester.requestFocus() } }
    val focusRequesterModifier = remember { Modifier.focusRequester(passwordFocusRequester) }

    Scaffold(scaffoldState = scaffoldState) {
        Box(Modifier.fillMaxSize()) {
            Column(
                Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginField(
                    value = login,
                    onValueChange = onLoginChange,
                    onNext = onNext
                )
                PasswordField(
                    value = password,
                    onValueChange = onPasswordChange,
                    onDone = onDone,
                    modifier = focusRequesterModifier
                )
                SignInButton(onClick = onSignInClick, enabled = signInButtonEnabled)
            }
        }
    }
}

@Composable
fun LoginField(
    value: String,
    onValueChange: (String) -> Unit,
    onNext: () -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 24.dp, end = 24.dp),
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.email_hint)) },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = { onNext() })
    )
}

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp, start = 24.dp, end = 24.dp)
            .then(modifier),
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.password_hint)) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = PasswordVisualTransformation(),
        keyboardActions = KeyboardActions(onDone = { onDone() })
    )
}

@Composable
fun SignInButton(
    onClick: () -> Unit,
    enabled: Boolean
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(stringResource(R.string.sign_in_btn))
    }
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    LoginContent(
        login = "",
        password = "",
        signInButtonEnabled = true,
        onLoginChange = {},
        onPasswordChange = {},
        onSignInClick = {}
    )
}