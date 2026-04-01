package com.example.lab1.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lab1.screens.buttons.ButtonsScreen
import com.example.lab1.screens.checkboxes.CheckboxesScreen
import com.example.lab1.screens.chips.ChipsScreen
import com.example.lab1.screens.datepicker.DatePickerScreen
import com.example.lab1.screens.dialog.DialogScreen
import com.example.lab1.screens.divider.DividerScreen
import com.example.lab1.screens.main.MainScreen
import com.example.lab1.screens.progress.ProgressBarScreen
import com.example.lab1.screens.radio.RadioButtonsScreen
import com.example.lab1.screens.switches.SwitchScreen
import com.example.lab1.screens.timepicker.TimePickerScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: AppScreen.Main.name

    val title = when (currentRoute) {
        AppScreen.Main.name -> "Lab 3"
        AppScreen.Buttons.name -> "Buttons"
        AppScreen.Checkboxes.name -> "Checkboxes"
        AppScreen.Chips.name -> "Chips"
        AppScreen.DatePicker.name -> "Datepicker dialog"
        AppScreen.Dialog.name -> "Dialog"
        AppScreen.Divider.name -> "Divider"
        AppScreen.ProgressBar.name -> "Progress bar"
        AppScreen.RadioButtons.name -> "Radio buttons"
        AppScreen.Switch.name -> "Switch"
        AppScreen.TimePicker.name -> "Timepicker dialog"
        else -> "Lab 3"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    if (currentRoute != AppScreen.Main.name) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreen.Main.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(AppScreen.Main.name) {
                MainScreen(
                    onButtonsClick = { navController.navigate(AppScreen.Buttons.name) },
                    onCheckboxesClick = { navController.navigate(AppScreen.Checkboxes.name) },
                    onChipsClick = { navController.navigate(AppScreen.Chips.name) },
                    onDatePickerClick = { navController.navigate(AppScreen.DatePicker.name) },
                    onDialogClick = { navController.navigate(AppScreen.Dialog.name) },
                    onDividerClick = { navController.navigate(AppScreen.Divider.name) },
                    onProgressBarClick = { navController.navigate(AppScreen.ProgressBar.name) },
                    onRadioButtonsClick = { navController.navigate(AppScreen.RadioButtons.name) },
                    onSwitchClick = { navController.navigate(AppScreen.Switch.name) },
                    onTimePickerClick = { navController.navigate(AppScreen.TimePicker.name) }
                )
            }

            composable(AppScreen.Buttons.name) { ButtonsScreen() }
            composable(AppScreen.Checkboxes.name) { CheckboxesScreen() }
            composable(AppScreen.Chips.name) { ChipsScreen() }
            composable(AppScreen.DatePicker.name) { DatePickerScreen() }
            composable(AppScreen.Dialog.name) { DialogScreen() }
            composable(AppScreen.Divider.name) { DividerScreen() }
            composable(AppScreen.ProgressBar.name) { ProgressBarScreen() }
            composable(AppScreen.RadioButtons.name) { RadioButtonsScreen() }
            composable(AppScreen.Switch.name) { SwitchScreen() }
            composable(AppScreen.TimePicker.name) { TimePickerScreen() }
        }
    }
}