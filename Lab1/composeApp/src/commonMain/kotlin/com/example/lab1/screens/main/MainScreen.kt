package com.example.lab1.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    onButtonsClick: () -> Unit,
    onCheckboxesClick: () -> Unit,
    onChipsClick: () -> Unit,
    onDatePickerClick: () -> Unit,
    onDialogClick: () -> Unit,
    onDividerClick: () -> Unit,
    onProgressBarClick: () -> Unit,
    onRadioButtonsClick: () -> Unit,
    onSwitchClick: () -> Unit,
    onTimePickerClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Лабораторна робота №3",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Демонстрація компонентів Material 3",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(
            onClick = onButtonsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buttons")
        }

        Button(
            onClick = onCheckboxesClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Checkboxes")
        }

        Button(
            onClick = onChipsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Chips")
        }

        Button(
            onClick = onDatePickerClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Datepicker dialog")
        }

        Button(
            onClick = onDialogClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dialog")
        }

        Button(
            onClick = onDividerClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Divider")
        }

        Button(
            onClick = onProgressBarClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Progress bar")
        }

        Button(
            onClick = onRadioButtonsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Radio buttons")
        }

        Button(
            onClick = onSwitchClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Switch")
        }

        Button(
            onClick = onTimePickerClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Timepicker dialog")
        }
    }
}