package com.abdulkarim.posts

import androidx.compose.runtime.Composable

//@Composable
//fun PostListScreen(
//    posts: List<Post>,
//    onPostClick: (Post) -> Unit
//) {
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(8.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(posts) { post ->
//            PostItem(
//                post = post,
//                onClick = { onPostClick(post) }
//            )
//        }
//    }
//}
//
//data class Post(val id: String)