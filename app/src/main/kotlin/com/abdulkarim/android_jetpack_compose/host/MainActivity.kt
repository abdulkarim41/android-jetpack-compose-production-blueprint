package com.abdulkarim.android_jetpack_compose.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.abdulkarim.android_jetpack_compose.navigation.RootNavGraph
import com.abdulkarim.desingsystem.theme.AndroidjetpackcomposeproductionblueprintTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidjetpackcomposeproductionblueprintTheme {
                RootNavGraph()
            }
        }
    }
}