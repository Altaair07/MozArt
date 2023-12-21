package com.dicoding.mozart.navigation

sealed class Screen(val route : String) {
    object Splash : Screen(route = "splash")
    object Home : Screen(route = "home")
    object Detail : Screen("details") // Use curly braces for the dynamic segment
    object Favorite : Screen(route = "favorite")
}
