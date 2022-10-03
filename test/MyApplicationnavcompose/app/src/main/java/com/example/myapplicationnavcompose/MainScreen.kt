package com.example.myapplicationnavcompose


import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreenView(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigations(navController = navController) }
    ) {

        NavigationGraph(navController = navController)
    }
}