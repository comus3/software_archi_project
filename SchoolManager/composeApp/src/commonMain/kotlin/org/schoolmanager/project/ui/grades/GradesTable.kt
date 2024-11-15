// ui.grades/GradesTable.kt
package org.schoolmanager.project.ui.grades

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.Text
import org.schoolmanager.project.data.model.Grade
import org.schoolmanager.project.data.model.SubGrade
import org.schoolmanager.project.viewmodel.GradesViewModel

@Composable
fun GradesTable() {
    val viewModel = GradesViewModel()  // Obtenez le ViewModel
    Column(modifier = Modifier.padding(16.dp)) {

        Spacer(modifier = Modifier.height(16.dp))

        // Header row for months
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(2f)
            )

            Text("janvier", fontSize = 14.sp, fontWeight = FontWeight.Bold,modifier = Modifier.weight(1f),textAlign = TextAlign.Center)
            Text("juin", fontSize = 14.sp, fontWeight = FontWeight.Bold,modifier = Modifier.weight(1f),textAlign = TextAlign.Center)
            Text("septembre", fontSize = 14.sp, fontWeight = FontWeight.Bold,modifier = Modifier.weight(1f),textAlign = TextAlign.Center)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp) // Épaisseur de la ligne
                .background(Color.Gray) // Couleur de la ligne
        )


        Spacer(modifier = Modifier.height(8.dp))

        // Each course row
        /*GradeRow("3base30 Comptabilité et entrepreneuriat 5 ECTS", "16", "", "")
        SubGradeRow("3babm3T Entrepreneuriat 25%", "", "6", "")
        SubGradeRow("3base3C Comptabilité 75%", "", "19", "")

        GradeRow("3basg30 Stage 10 ECTS", "", "18", "")
        SubGradeRow("3basg3X Stage 100%", "", "18", "")

        GradeRow("3beal30 Electronic design 5 ECTS", "13.5", "13.5", "")
        SubGradeRow("3beal3C Electronique analogique 75%", "13.5", "", "")
        SubGradeRow("3beal3L Labo electronique analogique 25%", "", "", "14")*/


        // Display each grade
        viewModel.grades.value.forEach { grade ->
            if (grade is Grade){
                GradeRow(grade.subject, grade.jan, grade.jun, grade.sept)
            }
            if (grade is SubGrade) {
                SubGradeRow(grade.subject, grade.jan, grade.jun, grade.sept)
            }
        }
    }
}


