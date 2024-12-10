package org.schoolmanager.project.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Orientation(
    val id: Int,
    val name: String,
)

@Serializable
data class Syllabus(
    val id: Int,
    val idorientation: Int,
    val syllabus: String,
    val quantity: Int,
    val price: Double
)