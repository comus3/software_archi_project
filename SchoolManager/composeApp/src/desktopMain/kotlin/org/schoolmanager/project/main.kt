package org.schoolmanager.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.schoolmanager.project.ui.calendar.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "SchoolManager",
    ) {
        App()
    }
}