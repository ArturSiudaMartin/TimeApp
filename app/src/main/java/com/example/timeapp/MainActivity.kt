package com.example.timeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.timeapp.ui.theme.TimeAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    private val screenManager = ScreenManager().apply {
        //addScreen(FirstScreen())
        //addScreen(SecondScreen())
        addScreen(ThirdScreen())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeAppTheme {
                var currentScreen by remember {
                    mutableStateOf(screenManager.getCurrentScreen())
                }

                Surface(modifier = Modifier.fillMaxSize()) {
                    currentScreen?.Render()
                }
            }
        }
    }
}



