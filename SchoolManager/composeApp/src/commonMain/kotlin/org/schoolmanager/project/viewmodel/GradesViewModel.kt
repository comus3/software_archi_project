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
            SubGrade("3beal3L Labo electronique analogique 25%", "", "", "14"),

            Grade("3baeco30 Économie appliquée 5 ECTS", "15", "", ""),
            SubGrade("3baeco3A Microéconomie 50%", "15", "", ""),
            SubGrade("3baeco3B Macroéconomie 50%", "", "16", ""),

            // Second Grade and its SubGrades
            Grade("3bapro30 Programmation avancée 10 ECTS", "", "14", ""),
            SubGrade("3bapro3P Projet Java 60%", "", "14", ""),
            SubGrade("3bapro3L TP Programmation 40%", "13", "", ""),

            // Third Grade and its SubGrades
            Grade("3bamath30 Mathématiques discrètes 5 ECTS", "17", "", ""),
            SubGrade("3bamath3A Algèbre 70%", "16", "", ""),
            SubGrade("3bamath3B Théorie des graphes 30%", "", "", "18"),

            // Fourth Grade and its SubGrades
            Grade("3bachim30 Chimie organique 7 ECTS", "12", "13", ""),
            SubGrade("3bachim3L Labo chimie organique 30%", "", "", "14"),
            SubGrade("3bachim3T Théorie chimie organique 70%", "12", "", ""),

            // Fifth Grade and its SubGrades
            Grade("3batech30 Technologie web 8 ECTS", "", "16.5", ""),
            SubGrade("3batech3P Projet web 40%", "", "18", ""),
            SubGrade("3batech3D Développement front-end 30%", "15", "", ""),
            SubGrade("3batech3B Back-end 30%", "", "", "16")

        )
    )
}