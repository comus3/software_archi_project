package org.schoolmanager.project

import androidx.compose.animation.AnimatedVisibility

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import org.jetbrains.compose.ui.tooling.preview.Preview


import androidx.compose.material.*

//@Composable
//@Preview
//fun App() {
//    MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }
//        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
//        }
//    }
//}
@Composable
@Preview
fun App() {
    MaterialTheme {
        // State to manage navigation
        var showContacts by remember { mutableStateOf(false) }
        var selectedContact by remember { mutableStateOf<Contact?>(null) } // Track the selected contact

        // Main interface
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Button to navigate to Contacts or back
            Button(onClick = {
                showContacts = !showContacts
                selectedContact = null  // Reset contact selection when navigating back
            }) {
                Text(if (showContacts) "Back" else "Go to Contacts")
            }

            // Show either the main content, ContactsScreen, or ContactDetailScreen based on navigation state
            AnimatedVisibility(visible = !showContacts && selectedContact == null) {
                MainContent()
            }

            AnimatedVisibility(visible = showContacts && selectedContact == null) {
                ContactsScreen(onContactClick = { contact ->
                    selectedContact = contact  // Set the selected contact
                })
            }

            AnimatedVisibility(visible = selectedContact != null) {
                selectedContact?.let {
                    ContactDetailScreen(contact = it)  // Display the detailed screen for the selected contact
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the School Manager App!")
    }
}