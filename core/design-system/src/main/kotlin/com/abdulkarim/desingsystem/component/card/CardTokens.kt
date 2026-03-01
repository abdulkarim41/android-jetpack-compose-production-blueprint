package com.abdulkarim.desingsystem.component.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.abdulkarim.desingsystem.theme.AppThemeDefaults

object CardTokens {

    val shape @Composable get() = AppThemeDefaults.shapes.medium

    val filledColors @Composable
    get() = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )

    val outlinedColors @Composable
    get() = CardDefaults.outlinedCardColors()

    val elevation @Composable
    get() = CardDefaults.cardElevation(defaultElevation = 2.dp)

    val border
        @Composable get() = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )
}