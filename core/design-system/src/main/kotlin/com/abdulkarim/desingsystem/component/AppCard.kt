package com.abdulkarim.desingsystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abdulkarim.desingsystem.theme.AppThemeDefaults
import com.abdulkarim.desingsystem.token.SpacingToken

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.padding(SpacingToken.md),
        shape = AppThemeDefaults.shapes.medium
    ) {
        Column(modifier = Modifier.padding(SpacingToken.md), content = content)
    }
}