package com.dicoding.mozart.navigation

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dicoding.mozart.model.BottomBarItem
import com.dicoding.mozart.ui.model.Data
import com.dicoding.mozart.ui.model.MuseumItem
import com.dicoding.mozart.ui.screen.CameraScreen
import com.dicoding.mozart.ui.screen.DashboardScreen
import com.dicoding.mozart.ui.screen.DetailFavoriteScreen
import com.dicoding.mozart.ui.screen.DetailScreen
import com.dicoding.mozart.ui.screen.FavoriteScreen
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun HomeNavGraph(navHostController: NavHostController, paddingValues: PaddingValues, memesList: List<MuseumItem>, context: Context) {
    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = BottomBarItem.Dashboard.route
    ) {
        composable(route = BottomBarItem.Dashboard.route) {
            DashboardScreen(navHostController = navHostController, memesList = memesList)
        }

        composable(route = BottomBarItem.Profile.route) {
            CameraScreen(context = context)
        }

        composable(route = BottomBarItem.Setting.route) {
            FavoriteScreen(navHostController = navHostController)
        }
        composable(route = "fav") {
            FavoriteScreen(navHostController = navHostController)
        }
        composable(route = "detail/{id}/{img}/{cat}") { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id")
            val image = navBackStackEntry.arguments?.getString("img")
            val cat = navBackStackEntry.arguments?.getString("cat")
            var decode = URLDecoder.decode(id, StandardCharsets.UTF_8.toString())
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

            val museum = moshi.adapter(Data::class.java).fromJson(decode)

            if (id != null) {

                if (museum != null) {
                    if (cat != null) {
                        DetailScreen(
                            navHostController = navHostController,
                            id = museum.id,
                            category = cat,
                            nama =museum.museumName,
                            description = museum.description,
                            alamat = museum.alamat,
                            image = image,
                            item = museum.items
                        )
                    }
                }
            } else {
                Log.e("Error", "Missing arguments in DetailScreen")
                navHostController.popBackStack()
            }
        }
        composable(route = "detailfav/{category}/{name}/{id}") { navBackStackEntry ->
            val category = navBackStackEntry.arguments?.getString("category")
            val name = navBackStackEntry.arguments?.getString("name")
            val id = navBackStackEntry.arguments?.getString("id")
            if (category != null) {
                if (name != null) {
                    if (id != null) {
                        DetailFavoriteScreen(
                            navHostController = navHostController,
                            category = category,
                            name = name,
                            id = id
                        )
                    }
                }
            }
        }
    }
}