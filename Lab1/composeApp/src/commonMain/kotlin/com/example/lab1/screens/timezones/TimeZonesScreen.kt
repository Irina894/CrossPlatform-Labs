package com.example.lab1.screens.timezones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    selectedZones: SnapshotStateList<String>,
    modifier: Modifier = Modifier,
    openDialogSignal: Int = 0
) {
    val helper: TimeZoneHelper = remember { TimeZoneHelperImpl() }
    val allZones = remember { helper.getTimeZoneStrings() }

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(openDialogSignal) {
        if (openDialogSignal > 0) {
            showDialog = true
        }
    }

    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            top = 16.dp,
            bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + 96.dp
        )
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Your location",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = helper.currentTimeZone(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = helper.currentTime(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        item {
            Text(
                text = "Selected timezones",
                style = MaterialTheme.typography.titleMedium
            )
        }

        if (selectedZones.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 1.dp
                    )
                ) {
                    Text(
                        text = "No timezones selected",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        } else {
            items(selectedZones) { zone ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 1.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = zone,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "Time: ${helper.getTime(zone)}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Date: ${helper.getDate(zone)}",
                            style = MaterialTheme.typography.bodyMedium
                        )
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