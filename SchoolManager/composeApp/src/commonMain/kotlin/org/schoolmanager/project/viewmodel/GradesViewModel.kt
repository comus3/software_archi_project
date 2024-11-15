// viewmodel/GradesViewModel.kt
package org.schoolmanager.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import org.schoolmanager.project.data.model.Grade
import org.schoolmanager.project.data.model.SubGrade

class GradesViewModel : ViewModel() {
    val grades = mutableStateOf(
        listOf(
            Grade("3base30 Comptabilité et entrepreneuriat 5 ECTS", "16", "", ""),
            SubGrade("3babm3T Entrepreneuriat 25%", "", "6", ""),
            SubGrade("3base3C Comptabilité 75%", "", "19", ""),

            Grade("3basg30 Stage 10 ECTS", "", "18", ""),
            SubGrade("3basg3X Stage 100%", "", "18", ""),

            Grade("3beal30 Electronic design 5 ECTS", "13.5", "13.5", ""),
            SubGrade("3beal3C Electronique analogique 75%", "13.5", "", ""),
            SubGrade("3beal3L Labo electronique analogique 25%", "", "", "14")

        )
    )
}