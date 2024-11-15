package org.schoolmanager.project

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.schoolmanager.project.ui.grades.App
import org.schoolmanager.project.ui.profile.App
import org.schoolmanager.project.ui.calendar.App
import org.schoolmanager.project.ui.contacts.App
import org.schoolmanager.project.ui.ui.homepage.App

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App()
    }
}