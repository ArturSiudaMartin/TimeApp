package com.example.timeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.graphics.Color

val cascadiamono_regular_font = FontFamily(
    Font(R.font.cascadiamono_regular)
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
                    //myText("welcome", 25.0, 80)
                    LiveClock()
                }
        }
    }
}

@Composable
fun myText(header: String, height: Double, fontSize: Int, font: FontFamily, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxHeight()
    ){
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
fun BackGroundImage(message: String, height: Double, fontSize :Int,  modifier: Modifier = Modifier) {
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

fun GetTime():String
{
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    val current = LocalDateTime.now().format(formatter)
    return current.format(formatter)
}
fun GetDate():String
{
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val current = LocalDateTime.now().format(formatter)
        return current.format(formatter)
    }

@Composable
fun LiveClock()
{
    var time by remember { mutableStateOf(GetTime()) }
    var date by remember { mutableStateOf(GetDate()) }

    LaunchedEffect(Unit)
    {
        while (true) {
            time = GetTime()
            date = GetDate()
            delay(1000L) // update every second
        }
    }
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
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            myText(time, 75.0, 60, cascadiamono_regular_font)
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            myText(date, 135.0, 30,cascadiamono_regular_font)
        }

    }
}
}