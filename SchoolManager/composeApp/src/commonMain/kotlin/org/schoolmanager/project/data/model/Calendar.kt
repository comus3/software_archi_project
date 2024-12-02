package org.schoolmanager.project.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Calendar(
    val idcourse: Int,
    val startTime: String,
    val endTime: String,
    val hall: String,
    val date: String
)