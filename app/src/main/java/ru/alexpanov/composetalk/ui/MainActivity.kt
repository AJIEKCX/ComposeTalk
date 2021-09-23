package ru.alexpanov.composetalk.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.alexpanov.composetalk.ui.bottomsheet.BottomSheetActivity
import ru.alexpanov.composetalk.ui.counter.CounterActivity
import ru.alexpanov.composetalk.ui.login.compose.LoginComposeActivity
import ru.alexpanov.composetalk.ui.login.mvi.LoginMviActivity
import ru.alexpanov.composetalk.ui.login.mvi.LoginMviComposeActivity
import ru.alexpanov.composetalk.ui.login.mvp.LoginMvpActivity
import ru.alexpanov.composetalk.ui.login.mvp.LoginMvpComposeActivity
import ru.alexpanov.composetalk.ui.login.mvvm.LoginMvvmActivity
import ru.alexpanov.composetalk.ui.login.mvvm.LoginMvvmComposeActivity
import ru.alexpanov.composetalk.ui.login.mvvm.binding.LoginDataBindingActivity
import ru.alexpanov.composetalk.ui.login.mvvm.binding.LoginDataBindingComposeActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold {
                Box(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, LoginComposeActivity::class.java))
                        }) {
                            Text("Login Compose")
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, LoginMvpActivity::class.java))
                        }) {
                            Text("Login MVP")
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, LoginMvpComposeActivity::class.java))
                        }) {
                            Text("Login MVP Compose")
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, LoginMvvmActivity::class.java))
                        }) {
                            Text("Login MVVM")
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, LoginMvvmComposeActivity::class.java))
                        }) {
                            Text("Login MVVM Compose")
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, LoginDataBindingActivity::class.java))
                        }) {
                            Text("Login MVVM DataBinding")
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, LoginDataBindingComposeActivity::class.java))
                        }) {
                            Text("Login MVVM DataBinding Compose")
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, LoginMviActivity::class.java))
                        }) {
                            Text("Login MVI")
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, LoginMviComposeActivity::class.java))
                        }) {
                            Text("Login MVI Compose")
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, CounterActivity::class.java))
                        }) {
                            Text("Counter")
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, BottomSheetActivity::class.java))
                        }) {
                            Text("Bottom Sheet")
                        }
                    }
                }
            }
        }
    }
}