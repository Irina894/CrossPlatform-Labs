package com.example.lab1

import TimeZoneHelperImpl
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import lab1.composeapp.generated.resources.Res
import lab1.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun App() {
    // Якщо AppTheme не знайдено, можна тимчасово замінити на MaterialTheme
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }

        // Ініціалізуємо логер
        val logger = Logger.withTag("Lab2")

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {
                showContent = !showContent
                logger.i { "Button clicked. showContent: $showContent" }
            }) {
                Text("Click me!")
            }

            AnimatedVisibility(showContent) {
                // Використовуємо ваш TimeZoneHelperImpl
                val currentTime = remember { TimeZoneHelperImpl().currentTime() }

                // Логуємо час при появі контенту
                LaunchedEffect(currentTime) {
                    logger.i { "Displayed time: $currentTime" }
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text(
                        text = "Current time: $currentTime",
                        style = TextStyle(fontSize = 28.sp)
                    )
                }
            }
        }
    }
}