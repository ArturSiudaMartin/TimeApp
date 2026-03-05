package com.example.timeapp

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class UpdatingBox(private val leadBox: BoxConfig) : DrawableShape {

    @Composable
    override fun Draw() {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val screenHeightPx = constraints.maxHeight.toFloat()

            val myXVal = leadBox.xVal
            val myWidth = leadBox.boxWidth

            val startOfYear = LocalDate.of(LocalDate.now().year, 1, 1)
            val localDate = LocalDate.now()

            val timeSinceStart = ChronoUnit.DAYS.between(startOfYear, localDate)
            val percentDone = (timeSinceStart / 365f)
            val percentDoneDisplay = "%.1f".format(percentDone * 100)

            val myHeight = percentDone * leadBox.boxHeight
            val myYVal = leadBox.yVal + leadBox.boxHeight - myHeight

            val altBox = BoxConfig(
                xVal = myXVal,
                yVal = myYVal,
                boxWidth = myWidth,
                boxHeight = myHeight,
                boxColor = Color.White
            )

            MyBox(altBox).Draw()

            val boxCentreFraction = (myYVal + myHeight - 150.0f) / screenHeightPx
            MyText(
                text = "$percentDoneDisplay%",
                posFraction = boxCentreFraction,
                fontSize = 30,
                font = cascadiamono_regular_font,
                color = Color.DarkGray
            )
        }
    }
}