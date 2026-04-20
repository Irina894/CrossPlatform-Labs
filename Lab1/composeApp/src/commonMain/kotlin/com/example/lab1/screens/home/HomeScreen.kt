package com.example.lab1.screens.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.lab1.screens.meeting.MeetingTimeScreen
import com.example.lab1.screens.timezones.TimeZonesScreen

private enum class Tab {
    TimeZones,
    MeetingTime
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    selectedZones: SnapshotStateList<String>,
    onBackClick: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(Tab.TimeZones) }
    var openAddDialogSignal by remember { mutableStateOf(0) }

    val title = when (selectedTab) {
        Tab.TimeZones -> "World Clocks"
        Tab.MeetingTime -> "Meeting Planner"
    }

    Scaffold(
        topBar = {
            // Додаємо тінь та тональне виділення для TopAppBar
            Surface(
                shadowElevation = 6.dp,
                tonalElevation = 4.dp
            ) {
                TopAppBar(
                    title = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )
            }
        },
        bottomBar = {
            // Навігаційна панель з невеликим підйомом
            NavigationBar(
                tonalElevation = 8.dp,
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ) {
                NavigationBarItem(
                    selected = selectedTab == Tab.TimeZones,
                    onClick = { selectedTab = Tab.TimeZones },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Public,
                            contentDescription = "Timezones"
                        )
                    },
                    label = { Text("Clocks", fontWeight = if(selectedTab == Tab.TimeZones) FontWeight.Bold else FontWeight.Normal) }
                )

                NavigationBarItem(
                    selected = selectedTab == Tab.MeetingTime,
                    onClick = { selectedTab = Tab.MeetingTime },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = "Find Time"
                        )
                    },
                    label = { Text("Planner", fontWeight = if(selectedTab == Tab.MeetingTime) FontWeight.Bold else FontWeight.Normal) }
                )
            }
        },
        floatingActionButton = {
            if (selectedTab == Tab.TimeZones) {
                FloatingActionButton(
                    onClick = { openAddDialogSignal++ },
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    // Робимо кнопку більш виразною через тінь та форму
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.shadow(8.dp, RoundedCornerShape(16.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add timezone",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        // Додаємо м'який перехід між екранами
        Crossfade(
            targetState = selectedTab,
            label = "tab_transition",
            modifier = Modifier.padding(innerPadding)
        ) { tab ->
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                when (tab) {
                    Tab.TimeZones -> {
                        TimeZonesScreen(
                            selectedZones = selectedZones,
                            modifier = Modifier.fillMaxSize(),
                            openDialogSignal = openAddDialogSignal
                        )
                    }

                    Tab.MeetingTime -> {
                        MeetingTimeScreen(
                            selectedZones = selectedZones,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}