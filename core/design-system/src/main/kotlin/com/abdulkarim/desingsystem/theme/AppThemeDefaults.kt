package com.abdulkarim.desingsystem.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

object AppThemeDefaults {
    val colors: ColorScheme
        @Composable get() = MaterialTheme.colorScheme

    val typography: Typography
        @Composable get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable get() = MaterialTheme.shapes
}