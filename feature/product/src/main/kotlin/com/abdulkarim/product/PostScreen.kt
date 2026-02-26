package com.abdulkarim.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.abdulkarim.entity.product.ProductApiEntity
import com.abdulkarim.ui.NetworkErrorView

@Composable
fun PostListScreen(
    viewModel: PostViewModel = hiltViewModel(),
    onPostClick: (ProductApiEntity) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    OutlinedTextField(
        value = "search category",
        onValueChange = {

        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text(text = "Search products...") },
        singleLine = true
    )


    Column(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is UiState.Loading -> {
                LoadingView()
            }
            is UiState.ApiError -> {
                val error = state as UiState.ApiError
                NetworkErrorView(
                    message = error.message,
                    onRetry = {
                        viewModel.action(UiAction.FetchProductsApiAction)
                    }
                )
            }
            is UiState.ApiSuccess -> {
                val products = (state as UiState.ApiSuccess).data
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(products) { product ->
                        ProductItem(product)
                    }
                }
            }

        }

    }


}

@Composable
fun ProductItem(product: ProductApiEntity) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier.padding(8.dp)
        ) {

            AsyncImage(
                model = product.thumbnail,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2
            )

            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
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


