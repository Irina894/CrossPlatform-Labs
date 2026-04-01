package com.example.lab1.screens.datepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var selectedDateText by remember { mutableStateOf("No date selected") }

    val datePickerState = rememberDatePickerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Діалог вибору дати з відображенням вибраного значення.",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Selected date: $selectedDateText",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(onClick = { showDialog = true }) {
            Text("Open date picker")
        }

        OutlinedButton(
            onClick = { selectedDateText = "No date selected" }
        ) {
            Text("Clear date")
        }
    }

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val millis = datePickerState.selectedDateMillis

                        selectedDateText = if (millis != null) {
                            val localDate = Instant
                                .fromEpochMilliseconds(millis)
                                .toLocalDateTime(TimeZone.currentSystemDefault())
                                .date

                            "${localDate.dayOfMonth.toString().padStart(2, '0')}." +
                                    "${localDate.monthNumber.toString().padStart(2, '0')}." +
                                    localDate.year
                        } else {
                            "No date selected"
                        }

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
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}