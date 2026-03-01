package com.abdulkarim.desingsystem.component.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
internal fun AppTextFieldBase(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    variant: TextFieldVariant,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: ImageVector? = null,
    isError: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    type: TextFieldType = TextFieldType.TEXT,
) {

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val isPassword = type == TextFieldType.PASSWORD

    val visualTransformation = if (isPassword && !passwordVisible)
        PasswordVisualTransformation() else VisualTransformation.None

    val keyboardOptions = if (isPassword)
        KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default

    val trailingIcon: @Composable (() -> Unit)? =
        if (isPassword) {
            {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible)
                            Icons.Default.VisibilityOff
                        else
                            Icons.Default.Visibility,
                        contentDescription = null
                    )
                }
            }
        } else null

    val style = textFieldStyle(variant)

    when (variant) {

        TextFieldVariant.OUTLINED -> {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier,
                label = label?.let { { Text(it) } },
                placeholder = placeholder?.let { { Text(it) } },
                leadingIcon = leadingIcon?.let {
                    { Icon(it, contentDescription = null) }
                },
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                isError = isError,
                enabled = enabled,
                shape = style.shape,
                colors = style.colors,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
            )
        }

        else -> {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier,
                label = label?.let { { Text(it) } },
                placeholder = placeholder?.let { { Text(it) } },
                leadingIcon = leadingIcon?.let {
                    { Icon(it, contentDescription = null) }
                },
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                isError = isError,
                enabled = enabled,
                shape = style.shape,
                colors = style.colors,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
            )
        }
    }
}