package com.example.timeapp

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class SecondScreen : Screen {
    override val screenName = "Clock"

    @Composable
    override fun Render() {
        var showDot by remember { mutableStateOf(false) }


        LiveClock(0.12f, 60)
        LiveDate(0.80f, 50)

        val mainBox = BoxConfig(1000f, 750f, -1f, 750f, Color.LightGray)

        MyBox(mainBox, onClick = { showDot = !showDot }).Draw()
        UpdatingBox(mainBox).Draw()

        if (showDot) {
            Box(modifier = Modifier.fillMaxSize()) {
                Canvas(
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Center)
                ) {
                    drawCircle(color = Color.Red)
                }
            }
        }
    }
}


