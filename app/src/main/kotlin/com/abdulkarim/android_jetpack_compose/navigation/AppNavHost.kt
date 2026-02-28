package com.abdulkarim.android_jetpack_compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.abdulkarim.navigation.AppDestination
import com.abdulkarim.navigation.AppNavigator
import com.abdulkarim.navigation.NavigationEvent
import com.abdulkarim.splash.navigation.splashNavGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    navigator: AppNavigator
) {

    LaunchedEffect(Unit) {
        navigator.events.collect { event ->
            when (event) {

                is NavigationEvent.Navigate -> {
                    navController.navigate(event.route){
                        launchSingleTop = true
                        restoreState = true
                    }
                }

                is NavigationEvent.PopBack -> {
                    navController.popBackStack()
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = AppDestination.SplashGraph.route
    ) {
        splashNavGraph(navigator)
        authNavGraph(navigator)
        mainNavGraph(navigator)
    }
}