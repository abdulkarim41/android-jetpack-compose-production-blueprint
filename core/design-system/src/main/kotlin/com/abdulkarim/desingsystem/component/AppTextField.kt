package com.abdulkarim.desingsystem.component

import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.abdulkarim.desingsystem.theme.AppThemeDefaults

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, style = AppThemeDefaults.typography.bodyLarge) },
        shape = AppThemeDefaults.shapes.medium
    )
}