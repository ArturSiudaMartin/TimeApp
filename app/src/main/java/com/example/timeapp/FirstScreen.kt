package com.example.timeapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timeapp.ui.theme.TimeAppTheme
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.isActive
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import androidx.compose.runtime.setValue

val cascadiamono_regular_font = FontFamily(
    Font(R.font.cascadiamono_regular)
)


data class BoxConfig(
    var boxHeight: Float,  // var = mutable (has getter AND setter)
    var boxWidth: Float,
    var xVal: Float,
    var yVal: Float,
    var boxColor: Color
)

class FirstScreen : Screen {
    override val screenName = "Clock"

    @Composable
    override fun Render() {
        LiveClock(0.12f, 60)
        LiveDate(0.80f, 50)
        val mainBox = BoxConfig(1000f, 750f, -1f, 750f, Color.LightGray)
        myBox(config = mainBox)
        updatingBox(mainBox)
    }
}

    @Composable
    fun myText(
        text: String,
        heightFraction: Float,  // 0f = top, 1f = bottom
        fontSize: Int,
        font: FontFamily,
        color: Color = Color.White,
        modifier: Modifier = Modifier
    ) {
        BoxWithConstraints(
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = text,
                fontFamily = font,
                fontSize = fontSize.sp,
                lineHeight = 50.sp,
                textAlign = TextAlign.Center,
                color = color,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = maxHeight * heightFraction - fontSize.dp / 2)
            )
        }
    }

    fun getTime(): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val current = LocalDateTime.now().format(formatter)
        return current.format(formatter)
    }

    fun getDate(): String {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return LocalDateTime.now().format(formatter)
    }

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
            myText(time, myHeight, myFontSize, cascadiamono_regular_font, Color.White)
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
            myText(date, myHeight, myFontSize, cascadiamono_regular_font, Color.White)
        }
    }

    @Composable
    fun myBox(config: BoxConfig) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize())
        {
            val screenWidth = constraints.maxWidth.toFloat()
            val screenHeight = constraints.maxHeight.toFloat()


            var xVar = if (config.xVal < 0) screenWidth / 2 - config.boxWidth / 2 else config.xVal
            var yVar = if (config.yVal < 0) screenHeight / 2 - config.boxHeight / 2 else config.yVal

            Canvas(modifier = Modifier.fillMaxSize())
            {
                drawRoundRect(
                    color = config.boxColor,
                    topLeft = Offset(xVar, yVar),
                    size = Size(config.boxWidth, config.boxHeight),
                    cornerRadius = CornerRadius(50f)
                )
            }
        }
    }
@Composable
fun updatingBox(leadBox: BoxConfig) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val screenHeightPx = constraints.maxHeight.toFloat()

        val myXVal = leadBox.xVal
        val myWidth = leadBox.boxWidth

        val startOfYear = LocalDate.of(LocalDate.now().year, 1, 1)
        val localDate = LocalDate.now()

        val timeSinceStart = ChronoUnit.DAYS.between(startOfYear, localDate)
        val perCentDone = (timeSinceStart / 365f)
        val perCentDoneDisplay = "%.1f".format(perCentDone * 100)

        val myHeigth = perCentDone * leadBox.boxHeight
        val myYVal = leadBox.yVal + leadBox.boxHeight - myHeigth

        val altBox = BoxConfig(myHeigth, myWidth, myXVal, myYVal, Color.White)
        myBox(config = altBox)

        val boxCentreFraction = (myYVal + myHeigth - 150.0f) / screenHeightPx
        myText(
            "$perCentDoneDisplay%",
            boxCentreFraction,
            30,
            cascadiamono_regular_font,
            Color.DarkGray
        )
    }
}