package com.abdulkarim.posts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.abdulkarim.common.base.Result
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.abdulkarim.entity.PostApiEntity

@Composable
fun PostListScreen(
    viewModel: PostViewModel = hiltViewModel(),
    onPostClick: (PostApiEntity) -> Unit
) {
    val state by viewModel.postState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is Result.Loading -> {
                LoadingView()
            }
            is Result.Error -> {
                val error = state as Result.Error
                ErrorView(
                    message = error.message,
                    onRetry = { viewModel.fetchPosts() }
                )
            }
            is Result.Success -> {
                val posts = (state as Result.Success).data
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(posts) { post ->
                        PostItem(
                            post = post,
                            onClick = { onPostClick(post) }
                        )
                    }
                }
            }

        }

    }


}

@Composable
fun PostItem(
    post: PostApiEntity,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            // Title
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Body
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3
            )
        }
    }
}

@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, color = Color.Red, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text("পুনরায় চেষ্টা করুন")
        }
    }
}

@Preview
@Composable
fun PostListPreview() {
//    val samplePosts = listOf(
//        Post(1, "Hello World", "This is sample post body"),
//        Post(2, "Compose UI", "Jetpack Compose is awesome"),
//        Post(3, "Clean Architecture", "Scalable app architecture")
//    )
//
//    PostListScreen(
//        posts = samplePosts,
//        onPostClick = {}
//    )
}

//data class Post(val id: Int, val title: String, val body: String)