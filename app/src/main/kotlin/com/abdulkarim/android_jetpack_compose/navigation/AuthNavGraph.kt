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
                appNavigator.navigate(
                    AppDestination.Login,
                    popUpTo = AppDestination.Onboarding.route,
                    inclusive = true
                )
            })
        }

        composable(AppDestination.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    appNavigator.navigate(
                        AppDestination.MainGraph,
                        popUpTo = AppDestination.AuthGraph.route,
                        inclusive = true
                    )
                }
            )
        }
    }
}