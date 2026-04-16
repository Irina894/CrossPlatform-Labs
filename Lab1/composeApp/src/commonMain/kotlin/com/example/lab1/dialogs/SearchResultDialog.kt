package com.example.lab1.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
@Composable
fun SearchResultDialog(
    resultHours: List<Int>,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        },
        title = {
            Text("Meeting time result")
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (resultHours.isEmpty()) {
                    Text(
                        text = "No suitable meeting hours were found for the selected interval.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                } else {
                    Text(
                        text = "Available hours:",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    resultHours.forEach { hour ->
                        Text(hour.toString().padStart(2, '0') + ":00")
                    }
                }
            }
        }
    )
}