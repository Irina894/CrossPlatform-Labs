package com.example.lab1.screens.root

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
fun RootScreen(
    onLab3Click: () -> Unit,
    onLab4Click: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Course project navigation",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Choose which laboratory work you want to demonstrate.",
            style = MaterialTheme.typography.bodyLarge
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Lab 3",
                    style = MaterialTheme.typography.titleMedium
                )

                Text("Material 3 components demonstration.")

                Button(
                    onClick = onLab3Click,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Open Lab 3")
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Lab 4",
                    style = MaterialTheme.typography.titleMedium
                )

                Text("Timezone app with search for suitable meeting time.")

                OutlinedButton(
                    onClick = onLab4Click,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Open Lab 4")
                }
            }
        }
    }
}