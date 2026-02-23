package com.abdulkarim.android_jetpack_compose.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.abdulkarim.posts.PostListScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = PostListScreenRoute
    ) {
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

@Composable
fun DetailsScreen(postId: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = " $postId Details Screen",textAlign = TextAlign.Center)
    }
}