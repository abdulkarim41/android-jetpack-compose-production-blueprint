package com.abdulkarim.android_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.abdulkarim.android_jetpack_compose.navigation.AppNavigation
import com.abdulkarim.android_jetpack_compose.navigation.LoginScreenRoute
import com.abdulkarim.android_jetpack_compose.navigation.PostListScreenRoute
import com.abdulkarim.desingsystem.theme.AndroidjetpackcomposeproductionblueprintTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: StartupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition {
            viewModel.uiState.value is StartupState.Loading
        }

        setContent {
            AndroidjetpackcomposeproductionblueprintTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        AppEntry()
                    }
                }
            }
        }
    }
}

@Composable
fun AppEntry(viewModel:StartupViewModel = hiltViewModel()) {

    val state by viewModel.uiState.collectAsState()

    when (state) {
        StartupState.Loading -> {
            // Nothing — splash still visible
        }

        StartupState.Authenticated -> {
            AppNavigation(startDestination = PostListScreenRoute)
        }

        StartupState.Unauthenticated -> {
            AppNavigation(startDestination = LoginScreenRoute)
        }
    }
}