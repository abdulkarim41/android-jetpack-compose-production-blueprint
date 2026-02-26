package com.abdulkarim.onboarding

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.LocalGroceryStore
import androidx.compose.material.icons.outlined.RemoveShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCartCheckout
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                OnboardingViewModel.UiEvent.NavigateNext -> {
                    onFinish()
                }
            }
        }
    }

    val pages = listOf(
        OnboardingPage(
            title = "Discover Amazing Products",
            description = "Browse thousands of curated products across multiple categories tailored to your needs.",
            icon = Icons.Outlined.AddShoppingCart
        ),
        OnboardingPage(
            title = "Smart Search & Filters",
            description = "Find exactly what you need with intelligent search and category filtering.",
            icon = Icons.Outlined.ShoppingCartCheckout
        ),
        OnboardingPage(
            title = "Fast & Secure Checkout",
            description = "Enjoy a smooth and secure checkout experience with real-time order tracking.",
            icon = Icons.Outlined.LocalGroceryStore
        ),
        OnboardingPage(
            title = "Secure Checkout",
            description = "Enjoy a smooth and secure checkout experience with real-time order tracking.",
            icon = Icons.Outlined.RemoveShoppingCart
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextButton(onClick = {
                    viewModel.onNavigateToNext()
                }) {
                    Text("Skip")
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->

                OnboardingPageContent(pages[page])
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Indicators

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(pages.size) { index ->
                    val pageOffset = (
                            (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
                            ).absoluteValue

                    val targetFactor = 1f - pageOffset.coerceIn(0f, 1f)
                    val animatedWidth = lerp(10.dp, 44.dp, targetFactor)

                    val animatedColor = lerp(
                        Color.LightGray,
                        MaterialTheme.colorScheme.primary,
                        targetFactor
                    )

                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(width = animatedWidth, height = 10.dp)
                            .background(
                                color = animatedColor,
                                shape = CircleShape
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Buttons
            val isLastPage = pagerState.currentPage == pages.lastIndex

            val targetWidth = if (isLastPage) 340.dp else 140.dp

            val animatedWidth by animateDpAsState(
                targetValue = targetWidth,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = if (isLastPage) FastOutSlowInEasing else LinearOutSlowInEasing
                ),
                label = "button_width_anim"
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    modifier = Modifier
                        .padding(horizontal = 14.dp)
                        .width(animatedWidth)
                        .height(48.dp),
                    shape = MaterialTheme.shapes.small,
                    onClick = {
                        if (isLastPage) {
                            viewModel.onNavigateToNext()
                        } else {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    }
                ) {
                    AnimatedContent(
                        targetState = if (isLastPage) "Get Started" else "Next",
                        label = "text_fade"
                    ) { targetText ->
                        Text(text = targetText, maxLines = 1)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

        }

    }


}

@Composable
fun OnboardingPageContent(page: OnboardingPage) {

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .size(180.dp)
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = page.icon,
                contentDescription = null,
                modifier = Modifier.size(200.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = page.title,
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}

data class OnboardingPage(
    val title: String,
    val description: String,
    val icon: ImageVector
)