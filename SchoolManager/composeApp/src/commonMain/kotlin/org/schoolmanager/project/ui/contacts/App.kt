package org.schoolmanager.project.ui.contacts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.schoolmanager.project.viewmodel.ContactsViewModel

@Composable
@Preview
fun App(viewModel: ContactsViewModel = viewModel()) {
    MaterialTheme {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                viewModel.clearSelectedContact()
            }) {
                Text(if (viewModel.selectedContact.value == null) "Go to Contacts" else "Back")
            }

            AnimatedVisibility(visible = viewModel.selectedContact.value == null) {
                ContactsScreen(viewModel)
            }

            AnimatedVisibility(visible = viewModel.selectedContact.value != null) {
                viewModel.selectedContact.value?.let {
                    ContactDetailScreen(contact = it)
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
