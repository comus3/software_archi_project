// ui.grades/GradeRow.kt
package org.schoolmanager.project.ui.grades

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.Text
import androidx.compose.ui.Alignment

@Composable
fun GradeRow(courseTitle: String, jan: String, juin: String, sept: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = courseTitle,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.weight(2f)
        )
        Text(
            text = if (jan.isNotEmpty()) jan else "-",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = if (jan.isNotEmpty()) Color(0xFF4CAF50) else Color.Gray,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = if (juin.isNotEmpty()) juin else "-",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = if (juin.isNotEmpty()) Color(0xFF4CAF50) else Color.Gray,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = if (sept.isNotEmpty()) sept else "-",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = if (sept.isNotEmpty()) Color(0xFF4CAF50) else Color.Gray,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp) // Épaisseur de la ligne
            .background(Color.Gray) // Couleur de la ligne
    )
}


@Composable
fun SubGradeRow(subTitle: String, jan: String, juin: String, sept: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
//            .padding(start = 16.dp, top = 4.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = subTitle,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.weight(2f)
        )
        Text(
            text = if (jan.isNotEmpty()) jan else "-",
            fontSize = 14.sp,
            color = if (jan.isNotEmpty()) Color(0xFF4CAF50) else Color.Gray,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = if (juin.isNotEmpty()) juin else "-",
            fontSize = 14.sp,
            color = if (juin.isNotEmpty()) Color(0xFF4CAF50) else Color.Gray,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = if (sept.isNotEmpty()) sept else "-",
            fontSize = 14.sp,
            color = if (sept.isNotEmpty()) Color(0xFF4CAF50) else Color.Gray,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp) // Épaisseur de la ligne
            .background(Color.Gray) // Couleur de la ligne
    )
}