// ui.grades/GradesScreen.kt
package org.schoolmanager.project.ui.grades

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import org.jetbrains.compose.resources.painterResource
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.back
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyListScope
import org.schoolmanager.project.viewmodel.GradesViewModel


@Composable
fun GradesScreen(BackProfile: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(vertical = 10.dp) // Optionnel : ajoute des marges verticales
        //contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // Back Button and Title
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(Res.drawable.back),
                    contentDescription = "back to Profile Page",
                    modifier = Modifier
                        .size(75.dp)
                        .clickable { BackProfile() }
                        .padding(bottom = 32.dp)
                )
            }
        }

        // Profile Section
        item { ProfileSection() }

        // Academic Year Row
        item { AcademicYearRow("Année académique 2024-2025 4MIN") }

        // Grades Table
        item {
            GradesTable(GradesViewModel())
        }

        // Spacer at the bottom
        item { Spacer(modifier = Modifier.height(64.dp)) }
    }
}