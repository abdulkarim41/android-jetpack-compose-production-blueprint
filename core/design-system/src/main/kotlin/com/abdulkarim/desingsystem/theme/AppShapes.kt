package com.abdulkarim.desingsystem.theme

import androidx.compose.material3.Shapes
import androidx.compose.foundation.shape.RoundedCornerShape
import com.abdulkarim.desingsystem.token.ShapeToken

internal val AppShapes = Shapes(
    small = RoundedCornerShape(ShapeToken.Small),
    medium = RoundedCornerShape(ShapeToken.Medium),
    large = RoundedCornerShape(ShapeToken.Large)
)