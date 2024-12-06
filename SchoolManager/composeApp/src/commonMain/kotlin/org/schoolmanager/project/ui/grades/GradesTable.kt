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
//import org.schoolmanager.project.data.model.Grade
import org.schoolmanager.project.data.model.SubGrade
import org.schoolmanager.project.viewmodel.GradesViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun GradesTable(viewModel: GradesViewModel) {
    var errorMessage by remember { mutableStateOf("") }

    val results by viewModel._studentsCourses.collectAsState()

    LaunchedEffect(Unit) {
        try {
            viewModel.fetchGrades()
        } catch (e: Exception) {
            errorMessage = "Failed to fetch grades"
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        results.forEach { studentCourse ->
            Text(
                text = "Course: ${studentCourse.course}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            // Afficher les notes finales du cours principal
            val finalGrades = studentCourse.final_grades
            Text(text = "Final Grades: Janvier - ${finalGrades.jan}, Juin - ${finalGrades.jun}, Septembre - ${finalGrades.sept}")

            // Parcourir les sous-cours
            studentCourse.subgrades.forEach { subGrade ->
                Text(text = "SubCourse: ${subGrade.subcourse}")
                Text(
                    text = "SubCourse Grades: Janvier - ${subGrade.grades.jan}, Juin - ${subGrade.grades.jun}, Septembre - ${subGrade.grades.sept}"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

//    // UI
//    Column(modifier = Modifier.padding(16.dp)) {
//        // Affiche un message d'erreur si nécessaire
//        if (errorMessage.isNotEmpty()) {
//            Text(
//                text = errorMessage,
//                color = Color.Red,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(8.dp)
//            )
//        }
//
//        // Affichage brut des données JSON
//        Text(
//            text = results.toString(), // Transforme les données en texte brut
//            fontSize = 14.sp,
//            color = Color.Black,
//            modifier = Modifier.padding(8.dp)
//        )
//    }


//    Column(modifier = Modifier.padding(16.dp)) {
//        student?.grades?.forEach { studentCourse ->
//            // Afficher le cours principal
//            GradeRow(
//                courseTitle = studentCourse.course,
//                jan = studentCourse.final_grades.jan ?: "",
//                juin = studentCourse.final_grades.jun ?: "",
//                sept = studentCourse.final_grades.sept ?: ""
//            )
//
//            // Afficher les sous-cours
//            studentCourse.subgrades.forEach { subgrade ->
//                SubGradeRow(
//                    subTitle = subgrade.subcourse,
//                    jan = subgrade.grades.jan ?: "",
//                    juin = subgrade.grades.jun ?: "",
//                    sept = subgrade.grades.sept ?: ""
//                )
//            }
//        }
//    }
}