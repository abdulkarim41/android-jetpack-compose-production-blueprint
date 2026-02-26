package com.abdulkarim.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.abdulkarim.ui.NetworkErrorView

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToOnboarding: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is SplashUiEvent.NavigateToMain -> navigateToHome()
                is SplashUiEvent.NavigateToLogin -> navigateToLogin()
                is SplashUiEvent.NavigateToOnboarding -> navigateToOnboarding()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {

        when (val state = uiState) {
            is SplashUiState.Loading -> {
                CircularProgressIndicator()
            }
            is SplashUiState.ProfileApiError -> {
                NetworkErrorView(
                    message = state.message,
                    onRetry = {
                        viewModel.action(SplashUiAction.FetchProfileApiAction)
                    }
                )
            }
        }
    }

}