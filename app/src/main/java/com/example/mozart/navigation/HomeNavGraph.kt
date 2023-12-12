package com.example.mozart.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mozart.model.BottomBarItem
import com.example.mozart.ui.screen.DashboardScreen
import com.example.mozart.ui.screen.DetailScreen

@Composable
fun HomeNavGraph(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = BottomBarItem.Dashboard.route
    ) {
        composable(route = BottomBarItem.Dashboard.route) {
            DashboardScreen(navHostController = navHostController)
        }

        composable(route = BottomBarItem.Profile.route) {
            //  ProfileScreen()
        }

        composable(route = BottomBarItem.Setting.route) {
            //  SettingSceen()
        }
        composable(route = "detail/{id}") { navBackStackEntry ->
            val args = navBackStackEntry.arguments?.getBundle("detailArgs")

            if (args != null) {
                val id = args.getInt("id")
                val title = args.getString("title") ?: ""
                val description = args.getString("description") ?: ""
                val image = args.getInt("image") ?: 0

                DetailScreen(
                    navHostController = navHostController,
                    id = id,
                    title = title,
                    description = description,
                    image = image
                )
            } else {
                Log.e("Error", "Missing arguments in DetailScreen")
                navHostController.popBackStack()
            }
        }
    }
}