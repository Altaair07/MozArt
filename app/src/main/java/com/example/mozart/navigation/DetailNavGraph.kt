package com.example.mozart.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.mozart.ui.screen.DetailScreen

fun NavGraphBuilder.detailNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = Screen.Detail.route
    ) {
        composable(route = Screen.Detail.route) {
            DetailScreen(navHostController = navHostController)
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object Register : AuthScreen(route = "REGISTER")
    object ForgotPassword : AuthScreen(route = "FORGOT_PASSWORD")
    object KodeOTPScreen : AuthScreen(route = "KODE_OTP")
    object ResetPassword : AuthScreen(route = "RESET_PASSWORD")
}