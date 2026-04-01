package com.example.lab1.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import com.example.lab1.TimeZoneHelperImpl
import lab1.composeapp.generated.resources.Res
import lab1.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainScreen(
    onButtonsClick: () -> Unit,
    onCheckboxesClick: () -> Unit,
    onChipsClick: () -> Unit,
    onDatePickerClick: () -> Unit,
    onDialogClick: () -> Unit,
    onDividerClick: () -> Unit,
    onProgressBarClick: () -> Unit,
    onRadioButtonsClick: () -> Unit,
    onSwitchClick: () -> Unit,
    onTimePickerClick: () -> Unit
) {
    var showContent by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Лабораторна робота №3",
            style = MaterialTheme.typography.titleLarge
        )

        Button(
            onClick = {
                showContent = !showContent
                Logger.i { "Logger test." }
            }
        ) {
            Text(if (showContent) "Сховати" else "Показати поточний час")
        }

        AnimatedVisibility(showContent) {
            val currentTime = remember { TimeZoneHelperImpl().currentTime() }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = null
                )

                Text(
                    text = "Current time: $currentTime",
                    style = TextStyle(
                        fontSize = 28.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }

        Button(
            onClick = onButtonsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buttons")
        }

        Button(
            onClick = onCheckboxesClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Checkboxes")
        }

        Button(
            onClick = onChipsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Chips")
        }

        Button(
            onClick = onDatePickerClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Datepicker dialog")
        }

        Button(
            onClick = onDialogClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dialog")
        }

        Button(
            onClick = onDividerClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Divider")
        }

        Button(
            onClick = onProgressBarClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Progress bar")
        }

        Button(
            onClick = onRadioButtonsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Radio buttons")
        }

        Button(
            onClick = onSwitchClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Switch")
        }

        Button(
            onClick = onTimePickerClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Timepicker dialog")
        }
    }
}