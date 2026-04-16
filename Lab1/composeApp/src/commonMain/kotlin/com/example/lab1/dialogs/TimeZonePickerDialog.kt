package com.example.lab1.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TimeZonePickerDialog(
    allZones: List<String>,
    selectedZones: List<String>,
    onDismiss: () -> Unit,
    onSave: (List<String>) -> Unit
) {
    val tempSelected = remember { selectedZones.toMutableStateList() }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Choose timezones")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onSave(tempSelected)
                    onDismiss()
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        text = {
            LazyColumn {
                items(allZones) { zone ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = zone,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(1f)
                        )

                        Checkbox(
                            checked = zone in tempSelected,
                            onCheckedChange = { checked ->
                                if (checked) {
                                    if (zone !in tempSelected) tempSelected.add(zone)
                                } else {
                                    tempSelected.remove(zone)
                                }
                            }
                        )
                    }
                }
            }
        }
    )
}