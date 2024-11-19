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

//import org.schoolmanager.project.viewmodel.GradesViewModel

@Composable
fun GradesScreen(BackProfile: ()-> Unit) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Back Button and Title
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.back),
                contentDescription = "Settings",
                modifier = Modifier
                    .size(75.dp)
                    .clickable {BackProfile() }
                    .padding(bottom = 16.dp)
            )
        }

        ProfileSection()
        AcademicYearRow("Année académique 2024-2025 4MIN")
        GradesTable()
        Spacer(modifier = Modifier.weight(1f))
        //BottomNavigationBar()
    }
}