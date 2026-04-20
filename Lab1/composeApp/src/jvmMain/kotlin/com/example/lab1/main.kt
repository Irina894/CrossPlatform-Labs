package com.example.lab1

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

data class WindowInfo(
    val id: Int,
    val windowName: String,
    val state: WindowState
)

fun main() = application {
    var windowCount = 1
    val windows = remember {
        mutableStateListOf(
            WindowInfo(
                id = 0,
                windowName = "Lab 1",
                state = WindowState()
            )
        )
    }

    windows.forEachIndexed { index, windowInfo ->
        Window(
            onCloseRequest = {
                windows.removeAt(index)
                if (windows.isEmpty()) exitApplication()
            },
            title = windowInfo.windowName,
            state = windowInfo.state
        ) {
            MenuBar {
                Menu("File", mnemonic = 'F') {
                    val nextWindowState = rememberWindowState()

                    Item(
                        "New",
                        onClick = {
                            windowCount++
                            windows.add(
                                WindowInfo(
                                    id = windowCount,
                                    windowName = "Lab $windowCount",
                                    state = nextWindowState
                                )
                            )
                        },
                        shortcut = KeyShortcut(Key.N, ctrl = true)
                    )

                    Item(
                        "Close",
                        onClick = {
                            windows.removeAt(index)
                            if (windows.isEmpty()) exitApplication()
                        },
                        shortcut = KeyShortcut(Key.W, ctrl = true)
                    )

                    Separator()

                    Item(
                        "Exit",
                        onClick = ::exitApplication
                    )
                }
            }

            App()
        }
    }
}