package com.dicoding.mozart.navigation

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dicoding.mozart.ui.model.Museum
import com.dicoding.mozart.ui.model.MuseumItem
import com.dicoding.mozart.ui.screen.DashboardScreen
import com.dicoding.mozart.ui.screen.DetailScreen
import com.dicoding.mozart.ui.screen.HomeScreen
import com.dicoding.mozart.ui.screen.SplashScreen

@Composable
fun RootNavGraph(navHostController: NavHostController, memesList: List<MuseumItem>, context: Context) {
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Screen.Splash.route
    ){
        composable(route = Screen.Splash.route){
            SplashScreen(navHostController = navHostController)
        }
        composable(route = Screen.Home.route){
            HomeScreen(memesList = memesList, context = context)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}