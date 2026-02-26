package com.example.timeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.dotted_black_bg),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            alpha = 0.5F,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    //myText("welcome", 25.0, 80)
                    LiveClock(125.0, 60)
                    LiveDate(650.0, 50)
                    val mainBox = BoxConfig(1000f, 750f, -1f, 750f, Color.LightGray)
                    myBox(config = mainBox)
                    updatingBox(mainBox)

                }
            }
        }
    }
    @Composable
    fun myText(
        header: String,
        height: Double,
        fontSize: Int,
        font: FontFamily,
        modifier: Modifier = Modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.0f))
            Text(
                text = header,
                fontFamily = font,
                fontSize = fontSize.sp,
                lineHeight = 50.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = height.dp)
            )
        }
    }
    @Composable
    fun BackGroundImage(
        message: String,
        height: Double,
        fontSize: Int,
        modifier: Modifier = Modifier
    ) {
        // Create a box to overlap image and texts
        Box(modifier) {
            Image(
                painter = painterResource(id = R.drawable.dotted_black_bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.5F
            )
            /*myText(
            message,
            height,
            fontSize,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
         */
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
    fun LiveClock(myHeight: Double,myFontSize:Int) {
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
            myText(time, myHeight, myFontSize, cascadiamono_regular_font)
        }


    }
    @Composable
    fun LiveDate(myHeight: Double, myFontSize:Int) {

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
            myText(date, myHeight, myFontSize, cascadiamono_regular_font)
        }
    }
    @Composable
    fun updatingBox(leadBox: BoxConfig) {
        val myXVal = leadBox.xVal
        val myWidth = leadBox.boxWidth

        val startOfYear = LocalDate.of(LocalDate.now().year, 1, 1)
        val localDate = LocalDate.now()

        val timeSinceStart = ChronoUnit.DAYS.between(startOfYear, localDate)

        val myHeigth = (timeSinceStart / 365f) * leadBox.boxHeight
        
        val myYVal = leadBox.yVal + leadBox.boxHeight - myHeigth
        
        val altBox = BoxConfig(myHeigth, myWidth, myXVal, myYVal, Color.White)
        myBox(config = altBox)
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
}