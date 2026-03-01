package com.abdulkarim.desingsystem.component.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

object AppButton {

    @Composable
    fun Filled(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        size: ButtonSize = ButtonSize.MEDIUM,
        isLoading: Boolean = false,
        leadingIcon: ImageVector? = null
    ) {
        AppButtonBase(
            text = text,
            onClick = onClick,
            modifier = modifier,
            variant = ButtonVariant.FILLED,
            size = size,
            isLoading = isLoading,
            leadingIcon = leadingIcon
        )
    }

    @Composable
    fun Outlined(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        size: ButtonSize = ButtonSize.MEDIUM
    ) {
        AppButtonBase(
            text = text,
            onClick = onClick,
            modifier = modifier,
            variant = ButtonVariant.OUTLINED,
            size = size
        )
    }

    @Composable
    fun Destructive(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        AppButtonBase(
            text = text,
            onClick = onClick,
            modifier = modifier,
            variant = ButtonVariant.DESTRUCTIVE
        )
    }
}