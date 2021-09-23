package ru.alexpanov.composetalk.ui.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class CounterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var title by rememberSaveable { mutableStateOf("Counter") }
            MyHomePage(title = title, onTitleChanged = { title = "NewCounter" })
        }
    }

    @Composable
    fun MyHomePage(title: String, onTitleChanged: () -> Unit) {
        var counter by rememberSaveable { mutableStateOf(0) }

        Scaffold(
            topBar = {
                TopAppBar(title = { Text(title) }, actions = {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                onTitleChanged()
                            }
                            .padding(8.dp)
                    )
                })
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { counter++ }) {
                    Icon(Icons.Default.Add, contentDescription = "Increment")
                }
            }
        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("You have pushed the button this many times:")
                Text(counter.toString(), style = MaterialTheme.typography.h4)
            }
        }
    }
}