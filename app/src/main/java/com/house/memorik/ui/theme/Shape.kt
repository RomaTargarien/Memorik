package com.house.memorik.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

class BottomEllipseShape : Shape {

    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        return Outline.Generic(
            path = drawBottomEllipseShape(size)
        )
    }
}

private fun drawBottomEllipseShape(size: Size): Path {
    return Path().apply {
        val width = size.width
        val height = size.height
        moveTo(width * -0.5f, height * 0.6f)
        cubicTo(
            width.times(0.0f),
            height.times(1.0f),
            width.times(1.0f),
            height.times(1.0f),
            width.times(1.5f),
            height.times(0.6f)
        )
        lineTo(width * 1.0f, height * 0.0f)
        lineTo(width * 0.0f, height * 0.0f)
        close()
    }
}