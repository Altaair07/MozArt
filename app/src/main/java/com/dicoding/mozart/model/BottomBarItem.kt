package com.dicoding.mozart.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItem(
    val route : String,
    val title : String,
    val icon : ImageVector
){
    object Dashboard : BottomBarItem(
        route = "DASHBOARD",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Profile : BottomBarItem(
        route = "PROFILE",
        title = "Scan",
        icon = Icons.Default.Search
    )
    object Setting : BottomBarItem(
        route = "SETTING",
        title = "Favorite",
        icon = Icons.Default.FavoriteBorder
    )
}
