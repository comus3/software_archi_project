package org.schoolmanager.project.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.schoolmanager.project.data.model.Contact

class ContactsViewModel : ViewModel() {
    val contacts = mutableStateListOf(
        Contact("Olivier Dehareng", "Student"),
        Contact("John Doe", "Student"),
        Contact("Jane Smith", "Personal"),
        Contact("Alice Johnson", "Student")
    )

    var searchQuery = mutableStateOf("")
    var selectedContact = mutableStateOf<Contact?>(null)

    val filteredContacts
        get() = contacts.filter {
            it.name.contains(searchQuery.value, ignoreCase = true)
        }

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }

    fun onContactSelected(contact: Contact) {
        selectedContact.value = contact
    }

    fun clearSelectedContact() {
        selectedContact.value = null
    }
}
