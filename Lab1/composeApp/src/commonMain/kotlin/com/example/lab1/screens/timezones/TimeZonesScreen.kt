package com.example.lab1.screens.timezones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.example.lab1.dialogs.TimeZonePickerDialog

@Composable
fun TimeZonesScreen(
    selectedZones: SnapshotStateList<String>
) {
    val helper: TimeZoneHelper = remember { TimeZoneHelperImpl() }

    var showDialog by remember { mutableStateOf(false) }
    val allZones = remember { helper.getTimeZoneStrings() }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add timezone"
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Time zones",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Manage selected timezones and view their current date and time.",
                style = MaterialTheme.typography.bodyLarge
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Your timezone",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text("Zone: ${helper.currentTimeZone()}")
                    Text("Current time: ${helper.currentTime()}")
                }
            }

            Text(
                text = "Selected zones",
                style = MaterialTheme.typography.titleMedium
            )

            if (selectedZones.isEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "No timezones selected yet.",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(selectedZones) { zone ->
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(14.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = zone,
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Text("Time: ${helper.getTime(zone)}")
                                Text("Date: ${helper.getDate(zone)}")
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        TimeZonePickerDialog(
            allZones = allZones,
            selectedZones = selectedZones,
            onDismiss = { showDialog = false },
            onSave = { zones ->
                selectedZones.clear()
                selectedZones.addAll(zones.distinct())
            }
        )
    }
}