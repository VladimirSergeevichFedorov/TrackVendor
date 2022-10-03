package com.example.exchangerate.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.exchangerate.ui.viewmodels.PopularViewModule
import com.example.exchangerate.navigation.NavigationGraph
import com.example.exchangerate.ui.theme.ExchangeRateTheme
import com.example.exchangerate.ui.viewbar.BottomNavigationBar
import com.example.exchangerate.ui.viewbar.TopBar
import com.example.exchangerate.utils.StateSorted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExchangeRateTheme {
                val navController = rememberNavController()
                val viewModel = hiltViewModel<PopularViewModule>()
                val stateSearchText = remember { mutableStateOf(TextFieldValue("")) }
                val stateSorted = remember { mutableStateOf(StateSorted.NONE) }
                Scaffold(
                    topBar = {
                        TopBar(
                            stateSearchText = stateSearchText,
                            stateSorted = stateSorted
                        )
                    },
                    bottomBar = { BottomNavigationBar(navController) },
                    content = {
                        NavigationGraph(
                            navController = navController,
                            popularViewModule = viewModel,
                            stateSearchText = stateSearchText,
                            stateSorted = stateSorted
                        )
                    }
                )
            }
        }
    }
}