package com.example.timeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class FirstScreen : Screen {
    override val screenName = "YearView"

    @Composable
    override fun Render() {
        Background()
        LiveClock(0.18f, 60)
        LiveDate(0.80f, 50)

        val mainBox = BoxConfig(1000f, 750f, -1f, 750f, Color.LightGray)

        MyBox(mainBox).Draw()
        UpdatingBox(mainBox).Draw()

        navIcon(
            icon = R.drawable.calendar_icon,
            desc = "Calendar",
            align = Alignment.TopEnd,
            onClick = { screenCount++ }
        )    }
}

