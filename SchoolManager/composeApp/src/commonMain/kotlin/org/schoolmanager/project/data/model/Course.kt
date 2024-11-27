package org.schoolmanager.project.data.model

import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.DrawableResource

// Classe modèle pour les cours
data class Course(
    val name: String,
    val image: DrawableResource?= null,
    val startTime: String = "",
    val endTime: String = "",
    val hall: String = "",
    val date: LocalDate = LocalDate(1970, 1, 1)
)