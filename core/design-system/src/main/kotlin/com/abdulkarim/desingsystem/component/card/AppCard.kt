package com.abdulkarim.desingsystem.component.card

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

object AppCard {

    @Composable
    fun Filled(
        modifier: Modifier = Modifier,
        onClick: (() -> Unit)? = null,
        content: @Composable ColumnScope.() -> Unit
    ) {
        AppCardBase(
            modifier = modifier,
            variant = CardVariant.FILLED,
            onClick = onClick,
            content = content
        )
    }

    @Composable
    fun Outlined(
        modifier: Modifier = Modifier,
        onClick: (() -> Unit)? = null,
        content: @Composable ColumnScope.() -> Unit
    ) {
        AppCardBase(
            modifier = modifier,
            variant = CardVariant.OUTLINED,
            onClick = onClick,
            content = content
        )
    }
}