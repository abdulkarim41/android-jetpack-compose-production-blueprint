package com.abdulkarim.desingsystem.component.textfield

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.abdulkarim.desingsystem.theme.AppThemeDefaults
import com.abdulkarim.desingsystem.token.SpacingToken

data class TextFieldStyle(
    val colors: TextFieldColors,
    val shape: Shape
)

@Composable
fun textFieldStyle(variant: TextFieldVariant): TextFieldStyle {

    val colors = AppThemeDefaults.colors

    return when (variant) {

        TextFieldVariant.FILLED -> TextFieldStyle(
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colors.surface,
                unfocusedContainerColor = colors.surfaceVariant,
                focusedIndicatorColor = colors.primary,
                unfocusedIndicatorColor = colors.outline
            ),
            shape = RoundedCornerShape(SpacingToken.sm)
        )

        TextFieldVariant.OUTLINED -> TextFieldStyle(
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = colors.primary,
                unfocusedIndicatorColor = colors.outline,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(SpacingToken.sm)
        )

        TextFieldVariant.UNDERLINED -> TextFieldStyle(
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = colors.primary,
                unfocusedIndicatorColor = colors.outline
            ),
            shape = RoundedCornerShape(0.dp)
        )
    }
}