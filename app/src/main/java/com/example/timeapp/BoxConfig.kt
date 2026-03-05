package com.example.timeapp

import androidx.compose.ui.graphics.Color

data class BoxConfig(
    var boxHeight: Float,  // var = mutable (has getter AND setter)
    var boxWidth: Float,
    var xVal: Float,
    var yVal: Float,
    var boxColor: Color
)