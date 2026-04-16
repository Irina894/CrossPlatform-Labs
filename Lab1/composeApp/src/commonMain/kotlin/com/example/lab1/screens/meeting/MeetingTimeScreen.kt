package com.example.lab1.screens.meeting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab1.TimeZoneHelper
import com.example.lab1.TimeZoneHelperImpl
import com.example.lab1.components.TimeIntervalPicker
import com.example.lab1.dialogs.SearchResultDialog

@Composable
fun MeetingTimeScreen(
    selectedZones: SnapshotStateList<String>
) {
    val helper: TimeZoneHelper = remember { TimeZoneHelperImpl() }

    var startHour by remember { mutableStateOf(9) }
    var endHour by remember { mutableStateOf(18) }

    var showDialog by remember { mutableStateOf(false) }
    var resultHours by remember { mutableStateOf(emptyList<Int>()) }
    var errorText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Find meeting time",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Choose the start and end of the interval and search for suitable hours.",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Selected zones:",
            style = MaterialTheme.typography.titleMedium
        )

        if (selectedZones.isEmpty()) {
            Text("No zones selected. Go to Time zones and choose at least one.")
        } else {
            selectedZones.forEach { zone ->
                Text("• $zone")
            }
        }

        TimeIntervalPicker(
            label = "Start hour",
            value = startHour,
            onDecrease = {
                startHour = if (startHour > 0) startHour - 1 else 23
            },
            onIncrease = {
                startHour = if (startHour < 23) startHour + 1 else 0
            }
        )

        TimeIntervalPicker(
            label = "End hour",
            value = endHour,
            onDecrease = {
                endHour = if (endHour > 0) endHour - 1 else 23
            },
            onIncrease = {
                endHour = if (endHour < 23) endHour + 1 else 0
            }
        )

        if (errorText.isNotEmpty()) {
            Text(
                text = errorText,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {
                if (selectedZones.isEmpty()) {
                    errorText = "Select at least one timezone on the Time zones screen."
                    return@Button
                }

                if (startHour > endHour) {
                    errorText = "Start hour must be less than or equal to end hour."
                    return@Button
                }

                errorText = ""

                resultHours = helper.search(
                    startHour = startHour,
                    endHour = endHour,
                    timezoneStrings = selectedZones.toList()
                )

                showDialog = true
            }
        ) {
            Text("Search")
        }

        OutlinedButton(
            onClick = {
                startHour = 9
                endHour = 18
                errorText = ""
            }
        ) {
            Text("Reset interval")
        }
    }

    if (showDialog) {
        SearchResultDialog(
            resultHours = resultHours,
            onDismiss = { showDialog = false }
        )
    }
}