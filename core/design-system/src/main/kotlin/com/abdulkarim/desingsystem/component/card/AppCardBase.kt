package com.abdulkarim.desingsystem.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abdulkarim.desingsystem.token.SpacingToken

@Composable
internal fun AppCardBase(
    modifier: Modifier = Modifier,
    variant: CardVariant,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {

    val shape = CardTokens.shape

    //val cardModifier = modifier.padding(SpacingToken.md)

    when (variant) {

        CardVariant.FILLED -> {
            Card(
                modifier = modifier,
                shape = shape,
                colors = CardTokens.filledColors,
                elevation = CardTokens.elevation,
                onClick = onClick ?: {}
            ) {
                Column(
                    modifier = Modifier.padding(SpacingToken.md),
                    content = content
                )
            }
        }

        CardVariant.OUTLINED -> {
            OutlinedCard(
                modifier = modifier,
                shape = shape,
                colors = CardTokens.outlinedColors,
                border = CardTokens.border,
                onClick = onClick ?: {}
            ) {
                Column(
                    modifier = Modifier.padding(SpacingToken.md),
                    content = content
                )
            }
        }
    }
}