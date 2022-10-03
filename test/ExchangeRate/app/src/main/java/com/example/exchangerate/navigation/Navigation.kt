package com.example.exchangerate.navigation

sealed class Navigation(var route: String, var title: String) {
    object Popular : Navigation("popular",  "Популярное")
    object Favorites : Navigation("favorites",  "Избранное")
}