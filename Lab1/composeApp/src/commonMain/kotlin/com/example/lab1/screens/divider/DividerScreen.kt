package com.example.lab1.screens.divider

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DividerScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Divider", style = MaterialTheme.typography.titleLarge)
        Text("Text above divider", modifier = Modifier.padding(top = 16.dp))
        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
        Text("Text below divider")
    }
}