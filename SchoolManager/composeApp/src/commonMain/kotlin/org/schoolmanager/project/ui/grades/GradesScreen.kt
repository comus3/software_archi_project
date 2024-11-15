// ui.grades/GradesScreen.kt
package org.schoolmanager.project.ui.grades

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
//import org.schoolmanager.project.viewmodel.GradesViewModel

@Composable
fun GradesScreen() {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Back Button and Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* TODO: Handle back action */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF9C27B0))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("GRADES", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }

        ProfileSection()
        AcademicYearRow("Année académique 2024-2025 4MIN")
        GradesTable()
        Spacer(modifier = Modifier.weight(1f))
        //BottomNavigationBar()
    }
}