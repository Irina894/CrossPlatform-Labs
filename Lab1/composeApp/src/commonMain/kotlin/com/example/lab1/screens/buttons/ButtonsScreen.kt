package com.example.lab1.screens.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Приклади різних типів кнопок.",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(onClick = {}) {
            Text("Filled Button")
        }

        OutlinedButton(onClick = {}) {
            Text("Outlined Button")
        }

        ElevatedButton(onClick = {}) {
            Text("Elevated Button")
        }
    }
}