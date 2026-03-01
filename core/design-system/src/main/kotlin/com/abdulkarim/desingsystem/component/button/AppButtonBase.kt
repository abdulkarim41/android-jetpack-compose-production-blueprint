package com.abdulkarim.desingsystem.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.abdulkarim.desingsystem.theme.AppThemeDefaults

@Composable
internal fun AppButtonBase(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ButtonVariant,
    size: ButtonSize = ButtonSize.MEDIUM,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon: ImageVector? = null
) {

    val style = buttonStyle(variant)
    val sizeStyle = buttonSizeStyle(size)

    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = modifier.height(sizeStyle.height),
        shape = AppThemeDefaults.shapes.medium,
        colors = style.colors,
        border = style.border,
        contentPadding = PaddingValues(horizontal = sizeStyle.horizontalPadding)
    ) {

        if (isLoading) {
            CircularProgressIndicator(
                strokeWidth = 2.dp,
                modifier = Modifier.size(18.dp)
            )
        } else {

            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = text,
                style = sizeStyle.textStyle
            )
        }
    }
}