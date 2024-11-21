import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.schoolmanager.project.data.model.Contact

import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.back
import schoolmanager.composeapp.generated.resources.discord2
import schoolmanager.composeapp.generated.resources.images56

import schoolmanager.composeapp.generated.resources.gmail_ico
import schoolmanager.composeapp.generated.resources.linkedin
@Composable
fun ContactDetailScreen(contact: Contact?, onBack: () -> Unit) {
    if (contact == null) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "No contact selected.", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onBack) {
                Text("Go Back")
            }
        }
    } else {
        Row(
            Modifier
                .fillMaxWidth(),

            verticalAlignment = Alignment.Top // Alignement au sommet de la page
        ) {
            Image(
                painter = painterResource(Res.drawable.back),
                contentDescription = "Back",
                modifier = Modifier
                    .size(75.dp)
                    .clickable { onBack() }
                    .padding(0.dp) // Suppression de tout padding autour de l'image
            )
        }
        // Affichage des détails du contact
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Text(text = contact.name, style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(48.dp))
            Image(
                painter = painterResource(Res.drawable.images56),
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(250.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Type: ${contact.type}", style = MaterialTheme.typography.h4)

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(Res.drawable.discord2),
                    contentDescription = "Discord Logo",
                    modifier = Modifier.size(80.dp) // Taille ajustée
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(Res.drawable.gmail_ico),
                    contentDescription = "LinkedIn Logo",
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(Res.drawable.linkedin),
                    contentDescription = "Gmail Logo",
                    modifier = Modifier.size(100.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
