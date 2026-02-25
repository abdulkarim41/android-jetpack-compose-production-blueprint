package com.abdulkarim.android_jetpack_compose.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.abdulkarim.login.LoginScreen
import com.abdulkarim.onboarding.OnboardingScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {

    composable<Screen.Onboarding> {
        OnboardingScreen(onFinish = {
            navController.navigate(Screen.Login) {
                popUpTo<Screen.Onboarding> { inclusive = true }
            }
        })
    }

    composable<Screen.Login> {
        LoginScreen(
            onLoginSuccess = {
                navController.navigate(Screen.Main) {
                    popUpTo<Screen.Login> { inclusive = true }
                }
            }
        )
    }
}