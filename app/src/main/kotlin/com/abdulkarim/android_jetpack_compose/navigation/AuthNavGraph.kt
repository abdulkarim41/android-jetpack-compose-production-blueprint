package com.abdulkarim.android_jetpack_compose.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.abdulkarim.login.navigation.loginNavGraph
import com.abdulkarim.navigation.AppDestination
import com.abdulkarim.navigation.AppNavigator
import com.abdulkarim.onboarding.navigation.onboardingNavGraph

fun NavGraphBuilder.authNavGraph(appNavigator: AppNavigator) {

    navigation(
        startDestination = AppDestination.OnboardingGraph.route,
        route = AppDestination.AuthGraph.route
    ){
        onboardingNavGraph(appNavigator)
        loginNavGraph(appNavigator)
    }
}