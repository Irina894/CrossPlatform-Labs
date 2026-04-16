package com.example.lab1.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onTimeZonesClick: () -> Unit,
    onMeetingClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Timezone App",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "This app helps view timezones and find a suitable meeting time.",
            style = MaterialTheme.typography.bodyLarge
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Time zones page",
                    style = MaterialTheme.typography.titleMedium
                )

                Text("View your timezone and manage selected world timezones.")

                Button(
                    onClick = onTimeZonesClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Open Time zones")
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Meeting time page",
                    style = MaterialTheme.typography.titleMedium
                )

                Text("Choose an interval and search for suitable meeting hours.")

                OutlinedButton(
                    onClick = onMeetingClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Open Meeting time")
                }
            }
        }
    }
}