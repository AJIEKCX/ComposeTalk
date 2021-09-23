package ru.alexpanov.composetalk.ui.bottomsheet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

class BottomSheetActivity : FragmentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalMaterialNavigationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp()
        }
    }

    @ExperimentalMaterialNavigationApi
    @ExperimentalMaterialApi
    @Composable
    fun MyApp() {
        val navController = rememberNavController()
        val bottomSheetNavigator = rememberBottomSheetNavigator()
        navController.navigatorProvider += bottomSheetNavigator
        ModalBottomSheetLayout(bottomSheetNavigator) {
            NavHost(navController, "home") {
                composable(route = "home") {
                    Box(Modifier.fillMaxSize()) {
                        Button(
                            modifier = Modifier.align(Alignment.Center),
                            onClick = { navController.navigate("sheet") }
                        ) {
                            Text("Navigate")
                        }
                    }
                }
                bottomSheet(route = "sheet") {
                    val viewModel = viewModel<BottomSheetViewModel>()
                    BottomSheetContent(viewModel)
                }
            }
        }
    }

    @Composable
    fun BottomSheetContent(viewModel: BottomSheetViewModel) {
        val text by viewModel.text.collectAsState()
        Box(
            Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Text(text, Modifier.align(Alignment.Center))
        }
    }
}