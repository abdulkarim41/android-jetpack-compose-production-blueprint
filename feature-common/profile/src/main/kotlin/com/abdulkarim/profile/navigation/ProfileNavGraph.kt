package com.abdulkarim.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.abdulkarim.navigation.AppDestination
import com.abdulkarim.navigation.AppNavigator
import com.abdulkarim.profile.ProfileScreen

fun NavGraphBuilder.profileNavGraph(appNavigator: AppNavigator) {
    navigation(
        startDestination = AppDestination.Profile.route,
        route = AppDestination.ProfileGraph.route
    ) {

        composable(AppDestination.Profile.route) {
            ProfileScreen(onNavigateToLogin = {
                //appNavigator.navigate(AppDestination.LoginGraph)
            })
        }
    }
}