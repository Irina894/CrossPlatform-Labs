package com.example.lab1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab1.TimeZoneHelperImpl
import com.example.lab1.screens.home.HomeScreen
import com.example.lab1.screens.root.RootScreen

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
                onLab4Click = { navController.navigate(AppScreen.Lab4.name) }
            )
        }

        composable(AppScreen.Lab3.name) {
            Lab3Navigation()
        }

        composable(AppScreen.Lab4.name) {
            HomeScreen(
                selectedZones = selectedZones,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}