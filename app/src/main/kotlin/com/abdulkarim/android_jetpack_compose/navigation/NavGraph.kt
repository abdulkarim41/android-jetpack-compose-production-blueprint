package com.abdulkarim.android_jetpack_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abdulkarim.login.LoginScreen
import com.abdulkarim.onboarding.OnboardingScreen
import com.abdulkarim.posts.PostListScreen
import com.abdulkarim.splash.SplashScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: Any = Screen.Splash
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
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

        composable<Screen.Onboarding> {
            OnboardingScreen(onFinish = {
                navController.navigate(Screen.Login) {
                    popUpTo<Screen.Onboarding> { inclusive = true }
                }
            })
        }

        composable<Screen.Login> {
            LoginScreen(onLoginSuccess = {
                navController.navigate(Screen.Home) {
                    popUpTo<Screen.Login> { inclusive = true }
                }
            })
        }

        composable<Screen.Home> {
            PostListScreen(
                onPostClick = { item ->
                    navController.navigate(Screen.PostDetailScreenRoute(item.id))
                }
            )
        }
    }
}


