package com.example.timeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timeapp.ui.theme.TimeAppTheme
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.font.FontFamily

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
val myFont = GoogleFont("Lobster") // replace with any Google Font name
val myFontFamily = FontFamily(
    Font(R.font.CascadiaMono-Regular)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    BackGroundImage(
                        message = "Android",
                    )
                }
            }
        }
    }
}

@Composable
fun HeadText(header: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxHeight()
    ){
    Spacer(modifier = Modifier.fillMaxHeight(0.25f))
        Text(
            text = "Welcome",
            fontFamily = myFontFamily,
            fontSize = 60.sp,
            lineHeight = 80.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 30.dp)
        )
    }
}

@Composable
fun BackGroundImage(message: String, modifier: Modifier = Modifier) {
    // Create a box to overlap image and texts
    Box(modifier) {
        Image(
            painter = painterResource(id = R.drawable.dotted_black_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        HeadText(
            header = "Welcome",
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TimeAppTheme {
        HeadText("Android")
    }
}