package com.dicoding.mozart.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dicoding.mozart.ui.screen.SplashScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(
            route = Screen.Splash.route
        ){
            SplashScreen(navHostController = navController)
        }
    }
}