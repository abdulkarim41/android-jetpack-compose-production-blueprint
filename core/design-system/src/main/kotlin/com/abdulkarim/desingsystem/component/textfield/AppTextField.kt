package com.abdulkarim.desingsystem.component.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

object AppTextField {

    @Composable
    fun Outlined(
        modifier: Modifier = Modifier,
        value: String,
        onValueChange: (String) -> Unit,
        label: String? = null,
        type: TextFieldType = TextFieldType.TEXT,
        isError: Boolean = false,
    ) {
        AppTextFieldBase(
            value = value,
            onValueChange = onValueChange,
            label = label,
            modifier = modifier,
            variant = TextFieldVariant.OUTLINED,
            type = type,
            isError = isError
        )
    }

    @Composable
    fun Filled(
        modifier: Modifier = Modifier,
        value: String,
        onValueChange: (String) -> Unit,
        label: String? = null,
        type: TextFieldType = TextFieldType.TEXT,
        isError: Boolean = false,
    ) {
        AppTextFieldBase(
            value = value,
            onValueChange = onValueChange,
            label = label,
            modifier = modifier,
            variant = TextFieldVariant.FILLED,
            type = type,
            isError = isError
        )
    }
}