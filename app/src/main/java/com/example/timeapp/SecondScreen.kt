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

var monthIcon = R.drawable.j_icon;
class SecondScreen : Screen {
    override val screenName = "YearView"

    @Composable
    override fun Render()
    {
        Background()
        MyText(getYear(), posFractionH = 0.18f, fontSize = 60, color = Color.White )

        month_icon();

        navIcon(
            icon = R.drawable.house_icon,
            desc = "Home",
            align = Alignment.TopStart,
            onClick = { screenCount-- }
        )

        navIcon(
            icon = monthIcon,
            desc = "month",
            align = Alignment.TopEnd,
            onClick = { screenCount++ }
        )
    }

    fun month_icon()
    {
        val monthLetter = getMonth().get(0);

        when(monthLetter)
        {
            'J' -> monthIcon = R.drawable.j_icon
            'F' -> monthIcon = R.drawable.f_icon
            'M' -> monthIcon = R.drawable.m_icon
            'A' -> monthIcon = R.drawable.a_icon
            'O' -> monthIcon = R.drawable.o_icon
            'N' -> monthIcon = R.drawable.n_icon
            'D' -> monthIcon = R.drawable.d_icon
        }
    }
}
