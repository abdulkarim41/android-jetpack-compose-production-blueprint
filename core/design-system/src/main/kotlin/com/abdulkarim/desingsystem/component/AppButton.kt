package com.abdulkarim.desingsystem.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.abdulkarim.desingsystem.theme.AppThemeDefaults

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = AppThemeDefaults.shapes.medium
    ) {
        Text(
            text = text,
            style = AppThemeDefaults.typography.bodyLarge,
            color = AppThemeDefaults.colors.onPrimary
        )
    }
}

@Composable
fun AppOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        shape = AppThemeDefaults.shapes.medium,
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = AppThemeDefaults.colors.primary
        )
    ) {
        Text(
            text = text,
            style = AppThemeDefaults.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppButton(){
    AppButton(
        text = "Login",
        onClick = {},
    )
}
