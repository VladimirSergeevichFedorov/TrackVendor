package com.example.trackvendor

sealed class NavigationDestination(val destination: String) {
    object MainScreen : NavigationDestination("mainScreen")
    object TableScreen : NavigationDestination("tableScreen")
}