package com.example.lab1.screens.timepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var selectedTimeText by remember { mutableStateOf("No time selected") }

    val timePickerState = rememberTimePickerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Діалог вибору часу з відображенням результату.",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Selected time: $selectedTimeText",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(onClick = { showDialog = true }) {
            Text("Open time picker")
        }

        OutlinedButton(onClick = { selectedTimeText = "No time selected" }) {
            Text("Clear time")
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedTimeText =
                            "${timePickerState.hour.toString().padStart(2, '0')}:" +
                                    "${timePickerState.minute.toString().padStart(2, '0')}"
                        showDialog = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            },
            text = {
                TimePicker(state = timePickerState)
            }
        )
    }
}