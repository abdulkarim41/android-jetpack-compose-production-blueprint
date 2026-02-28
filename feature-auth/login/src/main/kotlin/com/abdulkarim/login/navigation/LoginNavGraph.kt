package com.abdulkarim.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.abdulkarim.login.LoginScreen
import com.abdulkarim.navigation.AppDestination
import com.abdulkarim.navigation.AppNavigator

fun NavGraphBuilder.loginNavGraph(appNavigator: AppNavigator) {
    navigation(
        startDestination = AppDestination.Login.route,
        route = AppDestination.LoginGraph.route
    ) {
        composable(AppDestination.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    appNavigator.navigate(
                        destination = AppDestination.MainGraph,
                        popUpTo = AppDestination.AuthGraph.route,
                        inclusive = true
                    )
                }
            )
        }
    }
}