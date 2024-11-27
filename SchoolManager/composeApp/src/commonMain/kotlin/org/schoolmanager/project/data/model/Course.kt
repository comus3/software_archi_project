package org.schoolmanager.project.data.model

import org.jetbrains.compose.resources.DrawableResource

//DATA COURSE OF SCHOOL
data class Course(
    val id: Int= 0,
    val name: String,
    val image: DrawableResource?= null,
)