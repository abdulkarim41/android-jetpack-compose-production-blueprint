package com.abdulkarim.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.abdulkarim.navigation.AppDestination
import com.abdulkarim.navigation.AppNavigator
import com.abdulkarim.splash.SplashScreen

//fun NavGraphBuilder.splashNavGraph(
//    appNavigator: AppNavigator
//) {
//    navigation<AppDestination.SplashGraph>(
//        startDestination = AppDestination.Splash.route
//    ) {
//
//        composable<AppDestination.Splash> {
//            SplashScreen(
//                navigateToOnboarding = {
//                    appNavigator.navigate(AppDestination.AuthGraph)
//                },
//                navigateToLogin = {
//                    appNavigator.navigate(AppDestination.Login)
//                },
//                navigateToHome = {
//                    appNavigator.navigate(AppDestination.MainGraph)
//                },
//            )
//        }
//
//    }
//}

fun NavGraphBuilder.splashNavGraph(appNavigator: AppNavigator) {
    navigation(
        startDestination = AppDestination.Splash.route,
        route = AppDestination.SplashGraph.route
    ) {
        composable(AppDestination.Splash.route) {
            SplashScreen(
                navigateToOnboarding = {
                    appNavigator.navigate(AppDestination.AuthGraph)
                },
                navigateToLogin = {
                    appNavigator.navigate(AppDestination.Login)
                },
                navigateToHome = {
                    appNavigator.navigate(AppDestination.MainGraph)
                }
            )
        }
    }
}