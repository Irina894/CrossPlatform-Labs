package com.example.lab1.screens.checkboxes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CheckboxesScreen() {
    var checked1 by remember { mutableStateOf(false) }
    var checked2 by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Приклади прапорців зі зміною стану.",
            style = MaterialTheme.typography.bodyLarge
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checked1,
                onCheckedChange = { checked1 = it }
            )
            Text("First checkbox")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checked2,
                onCheckedChange = { checked2 = it }
            )
            Text("Second checkbox")
        }
    }
}