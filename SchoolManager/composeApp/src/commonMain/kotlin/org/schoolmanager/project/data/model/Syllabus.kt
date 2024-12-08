package org.schoolmanager.project.data.model


data class Orientation(
    val id: Int,
    val name: String,
)

data class Syllabus(
    val id: Int,
    val idorientation: Int,
    val syllabus: String,
    var quantity: Int = 1,
    val price: Double
)