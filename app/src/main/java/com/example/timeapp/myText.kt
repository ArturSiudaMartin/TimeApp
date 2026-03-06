package com.example.timeapp

import android.R.attr.maxHeight
import android.R.attr.maxWidth
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.timeapp.R


@Composable
fun MyText(
    text: String,
    fontSize: Int = 16,
    posFractionW: Float = 0.5f,
    posFractionH: Float = 0.5f,
    font: FontFamily = cascadiamono_regular_font,
    color: Color = Color.Black
) {
    BoxWithConstraints(Modifier.fillMaxSize()) {

        Text(
            text = text,
            fontSize = fontSize.sp,
            fontFamily = font,
            color = color,
            modifier = Modifier.layout { measurable, constraints ->

                val placeable = measurable.measure(constraints)

                val x = (maxWidth * posFractionW).roundToPx() - placeable.width / 2
                val y = (maxHeight * posFractionH).roundToPx() - placeable.height / 2

                layout(constraints.maxWidth, constraints.maxHeight) {
                    placeable.place(x, y)
                }
            }
        )
    }
}
