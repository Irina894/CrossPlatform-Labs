package com.example.lab1.screens.switches

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
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
fun SwitchScreen() {
    var checked1 by remember { mutableStateOf(false) }
    var checked2 by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Кілька варіантів switch з різними станами.",
            style = MaterialTheme.typography.bodyLarge
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(
                checked = checked1,
                onCheckedChange = { checked1 = it }
            )
            Text(
                text = if (checked1) "Enabled" else "Disabled",
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(
                checked = checked2,
                onCheckedChange = { checked2 = it }
            )
            Text(
                text = if (checked2) "Initially ON" else "Initially OFF",
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(
                checked = true,
                onCheckedChange = null,
                enabled = false
            )
            Text("Disabled ON", modifier = Modifier.padding(start = 8.dp))
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(
                checked = false,
                onCheckedChange = null,
                enabled = false
            )
            Text("Disabled OFF", modifier = Modifier.padding(start = 8.dp))
        }
    }
}