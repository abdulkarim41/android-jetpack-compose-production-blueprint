package com.abdulkarim.desingsystem.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.abdulkarim.desingsystem.theme.AppThemeDefaults

data class ButtonStyle(
    val colors: ButtonColors,
    val border: BorderStroke? = null
)

@Composable
fun buttonStyle(variant: ButtonVariant): ButtonStyle {

    val colors = AppThemeDefaults.colors

    return when (variant) {

        ButtonVariant.FILLED -> ButtonStyle(
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary,
                contentColor = colors.onPrimary
            )
        )

        ButtonVariant.OUTLINED -> ButtonStyle(
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = colors.primary
            ),
            border = BorderStroke(1.dp, colors.primary)
        )

        ButtonVariant.TEXT -> ButtonStyle(
            colors = ButtonDefaults.textButtonColors(
                contentColor = colors.primary
            )
        )

        ButtonVariant.TONAL -> ButtonStyle(
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.secondaryContainer,
                contentColor = colors.onSecondaryContainer
            )
        )

        ButtonVariant.DESTRUCTIVE -> ButtonStyle(
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.error,
                contentColor = colors.onError
            )
        )
    }
}