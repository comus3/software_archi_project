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
    //val studentId by viewModel.studentId.collectAsState()
    val results by viewModel._studentsCourses.collectAsState()

    LaunchedEffect(Unit) {
        try {
            println("call of function fetchGrades from viewmodel (student)")
            viewModel.fetchGrades()
        } catch (e: Exception) {
            errorMessage = "Failed to fetch grades"
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
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
            Text("janvier", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text("juin", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text("septembre", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Each course row
        results.forEach { studentCourse ->
            // Display main course
            GradeRow(courseTitle = studentCourse.course, finalGrades = studentCourse.final_grades)

            // Display sub-courses
            studentCourse.subgrades.forEach { subGrade ->
                SubGradeRow(
                    subTitle = subGrade.subcourse,
                    jan = subGrade.grades.jan,
                    juin = subGrade.grades.jun,
                    sept = subGrade.grades.sept
                )
            }
        }
    }
}