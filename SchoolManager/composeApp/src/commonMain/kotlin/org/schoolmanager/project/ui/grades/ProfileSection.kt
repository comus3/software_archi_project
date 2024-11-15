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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(Res.drawable.zlatan),
                contentDescription = null,
                contentScale = ContentScale.Crop, // Étire et recadre l'image pour remplir le cercl
                modifier = Modifier
                    .size(50.dp) // Assure que l'image prend toute la Box
                    .clip(CircleShape) // Découpe l'image en cercle
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text("Zlatan Ibrahimovic", fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}