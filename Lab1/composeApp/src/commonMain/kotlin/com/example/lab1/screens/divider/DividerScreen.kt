package com.example.lab1.screens.divider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DividerScreen() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Приклади горизонтального та вертикального divider.",
            style = MaterialTheme.typography.bodyLarge
        )

        Text("Text above divider")
        HorizontalDivider()
        Text("Text below divider")

        Row(
            modifier = Modifier.height(50.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Left")
            VerticalDivider(modifier = Modifier.fillMaxHeight())
            Text("Center")
            VerticalDivider(modifier = Modifier.fillMaxHeight())
            Text("Right")
        }
    }
}