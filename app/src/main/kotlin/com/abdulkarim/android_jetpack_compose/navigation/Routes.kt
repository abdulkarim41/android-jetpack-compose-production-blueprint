package com.abdulkarim.android_jetpack_compose.navigation

import kotlinx.serialization.Serializable

@Serializable
object PostListScreenRoute

@Serializable
data class PostDetailScreenRoute(val postId: String)
@Serializable
object LoginScreenRoute
