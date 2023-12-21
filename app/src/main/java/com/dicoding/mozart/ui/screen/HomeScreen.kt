package com.dicoding.mozart.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dicoding.mozart.model.BottomBarItem
import com.dicoding.mozart.navigation.HomeNavGraph
import com.dicoding.mozart.ui.components.bottomBarAnimatedScroll
import com.dicoding.mozart.ui.model.Museum
import com.dicoding.mozart.ui.model.MuseumItem


@Composable
fun HomeScreen(navController: NavHostController = rememberNavController(), memesList: List<MuseumItem>, context: Context) {

    val bottomBarHeight = 56.dp
    val bottomBarOffsetHeightPx = remember {
        mutableStateOf(0f)
    }

    Scaffold(
        modifier = Modifier.bottomBarAnimatedScroll(
            height = bottomBarHeight,
            offsetHeightPx = bottomBarOffsetHeightPx
        ),
        bottomBar = {
            BottomNavigation(navController)
        }
    ) { paddingValues ->
        HomeNavGraph(navHostController = navController, paddingValues, memesList = memesList, context = context)
    }

}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val screen = listOf(
        BottomBarItem.Dashboard,
        BottomBarItem.Profile,
        BottomBarItem.Setting,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar {
        screen.forEach { items ->
            AddItem(
                screen = items,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = screen.title)
        },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        alwaysShowLabel = true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        colors = NavigationBarItemDefaults.colors()
    )
}