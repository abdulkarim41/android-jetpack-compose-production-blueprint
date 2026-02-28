package com.abdulkarim.desingsystem.theme

import androidx.compose.material3.*
import com.abdulkarim.desingsystem.token.ColorToken

internal val LightColorScheme = lightColorScheme(
    primary = ColorToken.Primary,
    primaryContainer = ColorToken.PrimaryContainer,
    secondary = ColorToken.Secondary,
    secondaryContainer = ColorToken.SecondaryContainer,
    tertiary = ColorToken.Tertiary,
    tertiaryContainer = ColorToken.TertiaryContainer,
    background = ColorToken.BackgroundLight,
    surface = ColorToken.SurfaceLight,
    onBackground = ColorToken.TextPrimaryLight,
    onSurface = ColorToken.TextPrimaryLight,
    error = ColorToken.Error
)

internal val DarkColorScheme = darkColorScheme(
    primary = ColorToken.PrimaryContainer,
    secondary = ColorToken.SecondaryContainer,
    tertiary = ColorToken.TertiaryContainer,
    background = ColorToken.BackgroundDark,
    surface = ColorToken.SurfaceDark,
    onBackground = ColorToken.TextPrimaryDark,
    onSurface = ColorToken.TextPrimaryDark,
    error = ColorToken.Error
)