package org.schoolmanager.project.data.model


data class Orientation(
    val id: Int= 0,
    val name: String= "",
)

data class Syllabus(
    val idorientation: Int= 0,
    val syllabus: String
)