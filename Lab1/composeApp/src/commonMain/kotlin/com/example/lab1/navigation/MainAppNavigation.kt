package com.example.lab1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab1.TimeZoneHelperImpl
import com.example.lab1.screens.home.HomeScreen
import com.example.lab1.screens.meeting.MeetingTimeScreen
import com.example.lab1.screens.root.RootScreen
import com.example.lab1.screens.timezones.TimeZonesScreen

@Composable
fun MainAppNavigation() {
    val navController = rememberNavController()

    val helper = remember { TimeZoneHelperImpl() }
    val selectedZones = remember {
        listOf(helper.currentTimeZone()).toMutableStateList()
    }

    NavHost(
        navController = navController,
        startDestination = AppScreen.Root.name
    ) {
        composable(AppScreen.Root.name) {
            RootScreen(
                onLab3Click = { navController.navigate(AppScreen.Lab3.name) },
                onLab4Click = { navController.navigate(AppScreen.Lab4Home.name) }
            )
        }

        composable(AppScreen.Lab3.name) {
            Lab3Navigation()
        }

        composable(AppScreen.Lab4Home.name) {
            HomeScreen(
                onTimeZonesClick = { navController.navigate(AppScreen.TimeZones.name) },
                onMeetingClick = { navController.navigate(AppScreen.MeetingTime.name) }
            )
        }

        composable(AppScreen.TimeZones.name) {
            TimeZonesScreen(
                selectedZones = selectedZones
            )
        }

        composable(AppScreen.MeetingTime.name) {
            MeetingTimeScreen(
                selectedZones = selectedZones
            )
        }
    }
}