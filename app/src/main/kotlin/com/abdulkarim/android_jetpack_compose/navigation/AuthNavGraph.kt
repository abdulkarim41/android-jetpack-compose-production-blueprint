package com.abdulkarim.android_jetpack_compose.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.abdulkarim.login.LoginScreen
import com.abdulkarim.navigation.AppDestination
import com.abdulkarim.navigation.AppNavigator
import com.abdulkarim.onboarding.OnboardingScreen

fun NavGraphBuilder.authNavGraph(appNavigator: AppNavigator) {

    navigation(
        startDestination = AppDestination.Onboarding.route,
        route = AppDestination.AuthGraph.route
    ){
        composable(AppDestination.Onboarding.route) {
            OnboardingScreen(onFinish = {
//            navController.navigate(Screen.Login) {
//                popUpTo<Screen.Onboarding> { inclusive = true }
//            }
                appNavigator.navigate(AppDestination.Login)

            })
        }

        composable(AppDestination.Login.route) {
            LoginScreen(
                onLoginSuccess = {
//                navController.navigate(Screen.Main) {
//                    popUpTo<Screen.Login> { inclusive = true }
//                }
                    appNavigator.navigate(
                        AppDestination.MainGraph
                    )
                }
            )
        }
    }
}