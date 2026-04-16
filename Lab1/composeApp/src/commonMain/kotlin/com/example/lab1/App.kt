package com.example.lab1

import androidx.compose.runtime.Composable
import com.example.lab1.navigation.MainAppNavigation
import com.example.lab1.theme.Lab3Theme

@Composable
fun App() {
    Lab3Theme {
        MainAppNavigation()
    }
}