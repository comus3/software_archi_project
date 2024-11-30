package org.schoolmanager.project
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.schoolmanager.project.ApiService
import org.schoolmanager.project.data.model.Contact

class ContactsViewModel : ViewModel() {
    val contacts = mutableStateOf<List<Contact>>(emptyList())

    var searchQuery = mutableStateOf("")
    var selectedContact = mutableStateOf<Contact?>(null)
    // Fonction pour récupérer les contacts depuis l'API
    fun fetchContacts() {
        viewModelScope.launch {
            // Appeler l'API pour obtenir les contacts
            val fetchedContacts = ApiService.fetchContacts()
            // Mettre à jour la liste des contacts dans l'état
            contacts.value = fetchedContacts
        }
    }
    val filteredContacts
        get() = contacts.value.filter {
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