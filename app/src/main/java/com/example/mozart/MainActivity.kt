package com.example.mozart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mozart.navigation.RootNavGraph
import com.example.mozart.ui.theme.MozArtTheme

class MainActivity : ComponentActivity() {
    lateinit var navController : NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MozArtTheme {
                navController = rememberNavController()
                RootNavGraph(navHostController = navController)
            }
        }
    }
}