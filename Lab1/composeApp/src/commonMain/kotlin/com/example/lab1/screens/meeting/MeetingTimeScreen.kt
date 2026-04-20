package com.example.lab1.screens.meeting

// Якщо ви використовуєте закруглені кути в Card або Button, переконайтеся, що є цей імпорт:
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lab1.TimeZoneHelper
import com.example.lab1.TimeZoneHelperImpl
import com.example.lab1.components.TimeIntervalPicker
import com.example.lab1.dialogs.SearchResultDialog

@Composable
fun MeetingTimeScreen(
    selectedZones: SnapshotStateList<String>,
    modifier: Modifier = Modifier
) {
    val helper: TimeZoneHelper = remember { TimeZoneHelperImpl() }
    var startHour by remember { mutableStateOf(9) }
    var endHour by remember { mutableStateOf(18) }
    var showDialog by remember { mutableStateOf(false) }
    var resultHours by remember { mutableStateOf(emptyList<Int>()) }
    var errorText by remember { mutableStateOf("") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        contentPadding = PaddingValues(
            top = 24.dp,
            bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + 32.dp
        )
    ) {
        item {
            Text(
                text = "Define your preferred time range to find the best slot for all participants.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // У MeetingTimeScreen заміни блок з Active Locations на цей:

        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f) // М'який фон замість рамки
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Active Locations",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    if (selectedZones.isEmpty()) {
                        Text("No zones selected.", style = MaterialTheme.typography.bodySmall)
                    } else {
                        // Використовуємо FlowRow або просто Column з гарними тегами
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            selectedZones.forEach { zone ->
                                Surface(
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = RoundedCornerShape(24.dp), // Овальні "чіпси"
                                    tonalElevation = 2.dp,
                                    modifier = Modifier.shadow(1.dp, RoundedCornerShape(24.dp))
                                ) {
                                    Text(
                                        text = "• $zone",
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Селектори часу (припускаємо, що TimeIntervalPicker вже має гарний дизайн)
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                TimeIntervalPicker(
                    label = "Start interval",
                    value = startHour,
                    onDecrease = { startHour = if (startHour > 0) startHour - 1 else 23 },
                    onIncrease = { startHour = if (startHour < 23) startHour + 1 else 0 }
                )
                TimeIntervalPicker(
                    label = "End interval",
                    value = endHour,
                    onDecrease = { endHour = if (endHour > 0) endHour - 1 else 23 },
                    onIncrease = { endHour = if (endHour < 23) endHour + 1 else 0 }
                )
            }
        }

        if (errorText.isNotEmpty()) {
            item {
                Surface(
                    color = MaterialTheme.colorScheme.errorContainer,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = errorText,
                        modifier = Modifier.padding(12.dp),
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }

        // Блок кнопок дії
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .shadow(6.dp, RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        if (selectedZones.isEmpty()) {
                            errorText = "Select at least one timezone first."
                            return@Button
                        }
                        if (startHour > endHour) {
                            errorText = "Start hour cannot be later than end hour."
                            return@Button
                        }
                        errorText = ""
                        resultHours = helper.search(startHour, endHour, selectedZones.toList())
                        showDialog = true
                    }
                ) {
                    Text("Search", fontWeight = FontWeight.Bold)
                }

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        startHour = 9
                        endHour = 18
                        errorText = ""
                    }
                ) {
                    Text("Reset interval")
                }
            }
        }
    }

    if (showDialog) {
        SearchResultDialog(
            resultHours = resultHours,
            onDismiss = { showDialog = false }
        )
    }
}