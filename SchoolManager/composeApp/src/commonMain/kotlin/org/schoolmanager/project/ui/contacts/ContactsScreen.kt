package org.schoolmanager.project.ui.contacts
import org.schoolmanager.project.data.model.Contact
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.schoolmanager.project.ContactsViewModel

import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.images56
import schoolmanager.composeapp.generated.resources.profilephoto
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun ContactsScreen(
    viewModel: ContactsViewModel,
    GoToContactDetailScreen: () -> Unit,
    GoToProfile: () -> Unit
) {
    // Collecte les données des StateFlow
    val contacts by viewModel.contacts.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredContacts by viewModel.filteredContacts.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchContacts()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp)
    ) {
        // Box pour la photo de profil
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 6.dp, end = 10.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.profilephoto),
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .align(Alignment.TopEnd) // Positionne dans le coin supérieur droit
                    .clip(CircleShape)
                    .size(65.dp)
                    .clickable { GoToProfile() }, // Appel de la fonction pour aller au profil
                contentScale = ContentScale.Crop
            )
        }

        // Titre de la page
        Text(
            text = "Contacts",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Barre de recherche
        TextField(
            value = searchQuery, // Utilise la valeur collectée
            onValueChange = viewModel::onSearchQueryChanged,
            placeholder = { Text("Search here...", fontWeight = FontWeight.Bold) },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon") },
            modifier = Modifier.fillMaxWidth(),


        )

        Spacer(modifier = Modifier.height(16.dp))

        // Liste des contacts
        LazyColumn {
            items(filteredContacts) { contact -> // Utilise les contacts filtrés collectés
                ContactCard(contact = contact, onClick = {
                    viewModel.onContactSelected(contact)
                    GoToContactDetailScreen()
                })
                Spacer(modifier = Modifier.height(16.dp)) // Espace entre les cartes
            }
        }
    }
}

@Composable
fun ContactCard(contact: Contact, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = contact.name, fontWeight = FontWeight.Bold)
                Text(text = contact.id, color = Color.Gray)
            }
            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(Res.drawable.images56),
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(65.dp)
            )
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Arrow Icon")
        }
    }
}