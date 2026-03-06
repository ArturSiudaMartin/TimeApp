package com.example.timeapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


class ThirdScreen : Screen {
    override val screenName = "DaysView"

    @Composable
    override fun Render()
    {
        Background()
        MyText(getMonth(), posFractionH = 0.12f, fontSize = 60, color = Color.White )
    }
}


