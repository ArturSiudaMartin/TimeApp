package com.example.timeapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun LiveClock(myHeight: Float, myFontSize: Int) {
    var time by remember { mutableStateOf(getTime()) }

    LaunchedEffect(Unit)
    {
        while (isActive) {
            time = getTime()
            delay(1000L) // update every second
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    )
    {
        MyText(
            text = time,
            fontSize = myFontSize,
            posFractionH = myHeight,
            font = cascadiamono_regular_font,
            color = Color.White
        )
    }
}

@Composable
fun LiveDate(myHeight: Float, myFontSize: Int) {

    var date by remember { mutableStateOf(getDate()) }

    LaunchedEffect(Unit) {
        while (isActive) {
            date = getDate()
            delay(1000L)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        MyText(
            text = date,
            fontSize = myFontSize,
            posFractionH = myHeight,
            font = cascadiamono_regular_font,
            color = Color.White
        )
    }
}

