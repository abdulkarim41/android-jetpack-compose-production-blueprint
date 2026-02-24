package com.abdulkarim.android_jetpack_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.abdulkarim.login.LoginScreen
import com.abdulkarim.posts.DetailsScreen
import com.abdulkarim.posts.PostListScreen

@Composable
fun AppNavigation(
    startDestination: Any,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<LoginScreenRoute> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(PostListScreenRoute) {
                        popUpTo<LoginScreenRoute> { inclusive = true }
                    }
                }
            )
        }


        composable<PostListScreenRoute> {
            PostListScreen(
                onPostClick = { item ->
                    navController.navigate(PostDetailScreenRoute(item.id))
                }
            )
        }
        composable<PostDetailScreenRoute> { backStackEntry ->
            val route: PostDetailScreenRoute = backStackEntry.toRoute()
            DetailsScreen(postId = route.postId)
        }

    }
}


