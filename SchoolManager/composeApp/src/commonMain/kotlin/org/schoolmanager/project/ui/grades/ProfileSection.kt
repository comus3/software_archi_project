// ui.grades/ProfileSection.kt
package org.schoolmanager.project.ui.grades

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.zlatan

@Composable
fun ProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Centre horizontalement le contenu
    ) {
        Box(
            modifier = Modifier
                .size(70.dp) // Taille augmentée pour une meilleure visibilité
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(Res.drawable.zlatan),
                contentDescription = null,
                contentScale = ContentScale.Crop, // Étire et recadre l'image pour remplir le cercle
                modifier = Modifier
                    .size(80.dp) // Assure que l'image prend toute la Box
                    .clip(CircleShape) // Découpe l'image en cercle
            )
        }
        Spacer(modifier = Modifier.height(16.dp)) // Espacement entre la photo et le texte
        Text(
            text = "Zlatan Ibrahimovic",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}