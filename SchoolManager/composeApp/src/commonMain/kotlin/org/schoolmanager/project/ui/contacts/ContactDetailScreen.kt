package org.schoolmanager.project.ui.contacts
import org.schoolmanager.project.data.model.Contact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ContactDetailScreen(contact: Contact) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Name: ${contact.name}")
        Text(text = "Type: ${contact.type}")
    }
}
