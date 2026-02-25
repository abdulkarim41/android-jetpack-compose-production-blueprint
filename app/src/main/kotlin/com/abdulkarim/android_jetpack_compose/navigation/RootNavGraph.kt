package com.abdulkarim.android_jetpack_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.abdulkarim.splash.SplashScreen

@Composable
fun RootNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash
    ) {

        composable<Screen.Splash> {
            SplashScreen(
                navigateToOnboarding = {
                    navController.navigate(Screen.Onboarding) {
                        popUpTo<Screen.Splash> { inclusive = true }
                    }
                },
                navigateToHome = {
                    navController.navigate(Screen.Home) {
                        popUpTo<Screen.Home> { inclusive = true }
                    }
                },
                navigateToLogin = {
                    navController.navigate(Screen.Login) {
                        popUpTo<Screen.Login> { inclusive = true }
                    }
                }
            )
        }

        navigation<Screen.AuthGraph>(startDestination = Screen.Onboarding) {
            authNavGraph(navController)
        }

        navigation<Screen.MainGraph>(startDestination = Screen.Main) {
            mainNavGraph(navController)
        }
    }
}

