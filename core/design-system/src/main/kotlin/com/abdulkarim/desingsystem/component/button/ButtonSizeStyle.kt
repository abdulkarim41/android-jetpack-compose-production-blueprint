package com.abdulkarim.desingsystem.component.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.abdulkarim.desingsystem.theme.AppThemeDefaults

data class ButtonSizeStyle(
    val height: Dp,
    val textStyle: TextStyle,
    val horizontalPadding: Dp
)

@Composable
fun buttonSizeStyle(size: ButtonSize): ButtonSizeStyle {

    val typo = AppThemeDefaults.typography

    return when (size) {
        ButtonSize.SMALL -> ButtonSizeStyle(
            height = 36.dp,
            textStyle = typo.labelMedium,
            horizontalPadding = 12.dp
        )
        ButtonSize.MEDIUM -> ButtonSizeStyle(
            height = 48.dp,
            textStyle = typo.bodyLarge,
            horizontalPadding = 16.dp
        )
        ButtonSize.LARGE -> ButtonSizeStyle(
            height = 56.dp,
            textStyle = typo.titleMedium,
            horizontalPadding = 20.dp
        )
    }
}