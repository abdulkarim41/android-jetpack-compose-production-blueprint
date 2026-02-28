package com.abdulkarim.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.abdulkarim.navigation.AppDestination
import com.abdulkarim.navigation.AppNavigator
import com.abdulkarim.splash.SplashScreen

fun NavGraphBuilder.splashNavGraph(appNavigator: AppNavigator) {
    navigation(
        startDestination = AppDestination.Splash.route,
        route = AppDestination.SplashGraph.route
    ) {
        composable(AppDestination.Splash.route) {
            SplashScreen(
                navigateToOnboarding = {
                    appNavigator.navigate(
                        AppDestination.AuthGraph,
                        popUpTo = AppDestination.SplashGraph.route,
                        inclusive = true
                    )
                },
                navigateToLogin = {
                    appNavigator.navigate(
                        AppDestination.Login,
                        popUpTo = AppDestination.SplashGraph.route,
                        inclusive = true
                    )
                },
                navigateToHome = {
                    appNavigator.navigate(
                        AppDestination.MainGraph,
                        popUpTo = AppDestination.SplashGraph.route,
                        inclusive = true
                    )
                }
            )
        }
    }
}