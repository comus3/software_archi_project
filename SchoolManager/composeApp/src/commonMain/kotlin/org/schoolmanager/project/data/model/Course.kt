package org.schoolmanager.project.data.model

import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource

data class Course(
    val id: Int= 0,
    val name: String= "",
    val image: DrawableResource?= null,
    val professor: String= "",
    val email: String= "",
    val pdf_file: String= "",
    val students: List<String>? = null
)

@Serializable
data class API_Course(
    val id: Int= 0,
    val name: String= "",
    val professor: String= "",
    val email: String= "",
    val pdf_file: String= "",
    val students: List<String>
)