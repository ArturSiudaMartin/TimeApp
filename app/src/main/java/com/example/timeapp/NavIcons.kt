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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun navIcon(
    icon:Int,
    desc:String,
    padS: Dp = 8.dp,
    padE: Dp = 8.dp,
    padT: Dp = 25.dp,
    padB: Dp = 25.dp,
    size: Dp = 75.dp,
    align: Alignment,
    onClick: () -> Unit           // ← Pass the click action in too
    )
{
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(icon),
            contentDescription = desc,
            modifier = Modifier
                .padding(start = padS, end = padE, top = padT, bottom = padB)            // ← Adds a small gap from the screen edge
                .size(size)             // Width and height the same
                .align(align)        // ← Top right
                .clickable {
                    onClick()
                }
        )
    }
}