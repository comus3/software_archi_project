package org.schoolmanager.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.schoolmanager.project.ui.calendar.CalendarScreen

@Composable
fun App() {
    MaterialTheme {
        CalendarScreen()
    }
}