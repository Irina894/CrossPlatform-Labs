package com.example.lab1.screens.chips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FilterChip
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChipsScreen() {
    var filterSelected by remember { mutableStateOf(false) }
    var inputSelected by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Кілька типів chips для різних сценаріїв.",
            style = MaterialTheme.typography.bodyLarge
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AssistChip(
                onClick = {},
                label = { Text("Assist chip") }
            )

            SuggestionChip(
                onClick = {},
                label = { Text("Suggestion chip") }
            )

            FilterChip(
                selected = filterSelected,
                onClick = { filterSelected = !filterSelected },
                label = { Text(if (filterSelected) "Selected filter" else "Filter chip") }
            )

            InputChip(
                selected = inputSelected,
                onClick = { inputSelected = !inputSelected },
                label = { Text(if (inputSelected) "Selected input" else "Input chip") }
            )
        }
    }
}