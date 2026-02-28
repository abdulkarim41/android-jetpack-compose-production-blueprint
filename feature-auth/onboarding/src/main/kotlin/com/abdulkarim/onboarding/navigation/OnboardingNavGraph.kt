package com.abdulkarim.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.abdulkarim.navigation.AppDestination
import com.abdulkarim.navigation.AppNavigator
import com.abdulkarim.onboarding.OnboardingScreen

fun NavGraphBuilder.onboardingNavGraph(appNavigator: AppNavigator) {
    navigation(
        startDestination = AppDestination.Onboarding.route,
        route = AppDestination.OnboardingGraph.route
    ) {
        composable(AppDestination.Onboarding.route) {
            OnboardingScreen(onFinish = {
                appNavigator.navigate(
                    AppDestination.Login,
                    popUpTo = AppDestination.Onboarding.route,
                    inclusive = true
                )
            })
        }
    }
}