package com.abdulkarim.android_jetpack_compose.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.abdulkarim.android_jetpack_compose.navigation.AppNavHost
import com.abdulkarim.desingsystem.theme.AppTheme
import com.abdulkarim.navigation.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            AppTheme {
                AppNavHost(
                    navController = navController,
                    navigator = navigator
                )

            }
        }
    }
}