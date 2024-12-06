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

@Composable
fun GradesTable(viewModel: GradesViewModel, studentId: String) {
    val student = viewModel.getStudentGrades(studentId)

    Column(modifier = Modifier.padding(16.dp)) {
        student?.grades?.forEach { studentCourse ->
            // Afficher le cours principal
            GradeRow(
                courseTitle = studentCourse.course,
                jan = studentCourse.final_grades.jan ?: "",
                juin = studentCourse.final_grades.jun ?: "",
                sept = studentCourse.final_grades.sept ?: ""
            )

            // Afficher les sous-cours
            studentCourse.subgrades.forEach { subgrade ->
                SubGradeRow(
                    subTitle = subgrade.subcourse,
                    jan = subgrade.grades.jan ?: "",
                    juin = subgrade.grades.jun ?: "",
                    sept = subgrade.grades.sept ?: ""
                )
            }
        }
    }
}