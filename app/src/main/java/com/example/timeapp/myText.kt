package com.example.timeapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp



@Composable
fun MyText(
    text: String,
    fontSize: Int = 16,
    posFraction: Float = 0.5f,
    font: FontFamily = FontFamily.Default,
    color: Color = Color.Black
) {
    Text(
        text = text,
        fontSize = fontSize.sp,
        fontFamily = font,
        color = color
    )
}
