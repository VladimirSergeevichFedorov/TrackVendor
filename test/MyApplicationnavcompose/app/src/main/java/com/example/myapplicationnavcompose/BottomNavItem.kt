package com.example.myapplicationnavcompose

sealed class BottomNavItem(var title:String, var screen_route:String){

    object Home : BottomNavItem("Home","home")
    object MyNetwork: BottomNavItem("My Network","my_network")
    object AddPost: BottomNavItem("Post","add_post")
    object Notification: BottomNavItem("Notification","notification")
    object Jobs: BottomNavItem("Jobs","jobs")
}