// ui.grades/AcademicYearRow.kt
package org.schoolmanager.project.ui.grades

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Text
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AcademicYearRow(academicYear: String) {
    Text(
        academicYear,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF3E60A0))
            .padding(8.dp),
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    )
}