package com.example.lab1.screens.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBarScreen() {
    var progress by remember { mutableStateOf(0.35f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Determinate та indeterminate індикатори прогресу.",
            style = MaterialTheme.typography.bodyLarge
        )

        Text("Linear determinate")
        LinearProgressIndicator(progress = { progress })

        Text("Linear indeterminate")
        LinearProgressIndicator()

        Text("Circular determinate")
        CircularProgressIndicator(progress = { progress })

        Text("Circular indeterminate")
        CircularProgressIndicator()

        Button(onClick = {
            progress += 0.1f
            if (progress > 1f) progress = 0f
        }) {
            Text("Increase progress")
        }

        OutlinedButton(onClick = { progress = 0f }) {
            Text("Reset progress")
        }
    }
}