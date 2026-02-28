package com.abdulkarim.product.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.abdulkarim.navigation.AppDestination
import com.abdulkarim.navigation.AppNavigator
import com.abdulkarim.product.PostListScreen

fun NavGraphBuilder.homeNavGraph(appNavigator: AppNavigator) {
    navigation(
        startDestination = AppDestination.Home.route,
        route = AppDestination.HomeGraph.route
    ) {
        composable(AppDestination.Home.route) {
            PostListScreen()
        }
    }
}