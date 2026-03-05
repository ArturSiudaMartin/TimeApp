package com.example.timeapp

import android.util.LayoutDirection
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density

// Shape class containing the composable
class MyBox(private val config: BoxConfig) : DrawableShape {

    @Composable
    override fun Draw() {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val screenWidth = constraints.maxWidth.toFloat()
            val screenHeight = constraints.maxHeight.toFloat()

            val xVar = if (config.xVal < 0) screenWidth / 2 - config.boxWidth / 2
            else config.xVal
            val yVar = if (config.yVal < 0) screenHeight / 2 - config.boxHeight / 2
            else config.yVal

            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRoundRect(
                    color = config.boxColor,
                    topLeft = Offset(xVar, yVar),
                    size = Size(config.boxWidth, config.boxHeight),
                    cornerRadius = CornerRadius(50f)
                )
            }
        }
    }
}
