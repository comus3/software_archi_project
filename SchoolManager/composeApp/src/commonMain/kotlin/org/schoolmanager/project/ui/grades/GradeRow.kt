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
import org.schoolmanager.project.data.model.GradeDetails

@Composable
fun GradeRow(courseTitle: String, finalGrades: GradeDetails) {
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
            text = finalGrades.jan,
            fontSize = 14.sp,
            color = when {
                finalGrades.jan == "-" -> Color.Gray
                finalGrades.jan.toDoubleOrNull() != null && finalGrades.jan.toDouble() < 10 -> Color(0xFFFF0000)
                finalGrades.jan.toDoubleOrNull() != null -> Color(0xFF4CAF50)
                else -> Color.Gray
            },
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = finalGrades.jun,
            fontSize = 14.sp,
            color = when {
                finalGrades.jun == "-" -> Color.Gray
                finalGrades.jun.toDoubleOrNull() != null && finalGrades.jun.toDouble() < 10 -> Color(0xFFFF0000)
                finalGrades.jun.toDoubleOrNull() != null -> Color(0xFF4CAF50)
                else -> Color.Gray
            },
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = finalGrades.sept,
            fontSize = 14.sp,
            color = when {
                finalGrades.sept == "-" -> Color.Gray
                finalGrades.sept.toDoubleOrNull() != null && finalGrades.sept.toDouble() < 10 -> Color(0xFFFF0000)
                finalGrades.sept.toDoubleOrNull() != null -> Color(0xFF4CAF50)
                else -> Color.Gray
            },
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray)
    )
}

@Composable
fun SubGradeRow(subTitle: String, jan: String, juin: String, sept: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
            //.padding(start = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = subTitle,
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(2f)
        )
        Text(
            text = jan,
            fontSize = 14.sp,
            color = when {
                jan == "-" -> Color.Gray
                jan.toDoubleOrNull() != null && jan.toDouble() < 10 -> Color(0xFFFF0000)
                jan.toDoubleOrNull() != null -> Color(0xFF4CAF50)
                else -> Color.Gray
            },
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = juin,
            fontSize = 14.sp,
            color = when {
                juin == "-" -> Color.Gray
                juin.toDoubleOrNull() != null && juin.toDouble() < 10 -> Color(0xFFFF0000)
                juin.toDoubleOrNull() != null -> Color(0xFF4CAF50)
                else -> Color.Gray
            },
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = sept ,
            fontSize = 14.sp,
            color = when {
                sept == "-" -> Color.Gray
                sept.toDoubleOrNull() != null && sept.toDouble() < 10 -> Color(0xFFFF0000)
                sept.toDoubleOrNull() != null -> Color(0xFF4CAF50)
                else -> Color.Gray
            },
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray)
    )
}