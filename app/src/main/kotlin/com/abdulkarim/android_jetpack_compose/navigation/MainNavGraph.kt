package com.abdulkarim.android_jetpack_compose.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abdulkarim.navigation.AppDestination
import com.abdulkarim.navigation.AppNavigator
import com.abdulkarim.product.navigation.homeNavGraph
import com.abdulkarim.profile.navigation.profileNavGraph

fun NavGraphBuilder.mainNavGraph(navigator: AppNavigator) {
    composable(AppDestination.MainGraph.route) {
        MainScreen(navigator = navigator)
    }
}

@Composable
fun MainScreen(navigator: AppNavigator) {

    val navController = rememberNavController()


    Scaffold(
        bottomBar = {
            NavigationBar {
                val items = listOf(
                    BottomNavItem.Home,
                    BottomNavItem.Profile
                )
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute(navController) == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AppDestination.HomeGraph.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            homeNavGraph(navigator)
            profileNavGraph(navigator)
        }
    }
}

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Home : BottomNavItem(
        route = AppDestination.Home.route,
        label = "Home",
        icon = Icons.Default.Home
    )

    data object Profile : BottomNavItem(
        route = AppDestination.Profile.route,
        label = "Profile",
        icon = Icons.Default.Person
    )
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
