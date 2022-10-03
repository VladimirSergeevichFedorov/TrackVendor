package com.example.exchangerate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.exchangerate.ui.viewmodels.PopularViewModule
import com.example.exchangerate.ui.screen.FavoritesScreen
import com.example.exchangerate.ui.screen.PopularScreen
import com.example.exchangerate.utils.StateSorted

@Composable
fun NavigationGraph(
    navController: NavHostController,
    popularViewModule: PopularViewModule,
    stateSearchText: MutableState<TextFieldValue>,
    stateSorted: MutableState<StateSorted>
) {
    NavHost(navController, startDestination = Navigation.Popular.route) {
        composable(Navigation.Popular.route) {
            PopularScreen(
                viewModel = popularViewModule,
                navController = navController,
                stateSearchText = stateSearchText,
                stateSorted = stateSorted
            )
        }
        composable(Navigation.Favorites.route) {
            FavoritesScreen()
        }

    }
}