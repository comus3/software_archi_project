package org.schoolmanager.project

import androidx.compose.ui.window.ComposeUIViewController
import org.schoolmanager.project.ui.grades.App
import org.schoolmanager.project.ui.profile.App
import org.schoolmanager.project.ui.calendar.App
import org.schoolmanager.project.ui.contacts.App
import org.schoolmanager.project.ui.ui.homepage.App

fun MainViewController() = ComposeUIViewController { App() }