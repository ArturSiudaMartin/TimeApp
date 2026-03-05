package com.example.timeapp


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.isActive
import androidx.compose.runtime.setValue

val cascadiamono_regular_font = FontFamily(
    Font(R.font.cascadiamono_regular)
)


class FirstScreen : Screen {
    override val screenName = "Clock"

    @Composable
    override fun Render() {
        LiveClock(0.12f, 60)
        LiveDate(0.80f, 50)
        val mainBox = BoxConfig(1000f, 750f, -1f, 750f, Color.LightGray)
        MyBox(mainBox).Draw()
        UpdatingBox(mainBox).Draw()
    }

    @Composable
    override fun Button(
        onClick: () -> Unit,
        modifier: Modifier,
        enabled: Boolean,
        elevation: ButtonElevation?,
        border: BorderStroke?,
        contentPadding: PaddingValues,
        interactionSource: MutableInteractionSource?,
        content: @Composable (RowScope.() -> Unit)
    ) {}
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
            MyText(time, myFontSize, 0.8f,cascadiamono_regular_font, Color.White)
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
            MyText(date, myFontSize, 0.2f,cascadiamono_regular_font, Color.White)
        }
    }

