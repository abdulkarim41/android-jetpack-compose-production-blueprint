package com.abdulkarim.android_jetpack_compose.navigation

import kotlinx.serialization.Serializable
import kotlin.String

@Serializable
sealed interface Screen {
    @Serializable data object Splash : Screen
    @Serializable data object Onboarding : Screen
    @Serializable data object Login : Screen
    @Serializable data object Home : Screen
    @Serializable data class PostDetailScreenRoute(val postId: String)
}
