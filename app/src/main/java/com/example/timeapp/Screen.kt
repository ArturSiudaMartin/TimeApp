package com.example.timeapp

import androidx.compose.runtime.Composable

interface Screen {
    val screenName: String

    @Composable
    fun Render()
}