package org.schoolmanager.project.data.model

import kotlinx.datetime.LocalDate

//DATA COURSE CALENDAR
data class CourseCalendar(
    val idcourse: Int,
    val startTime: String = "",
    val endTime: String = "",
    val hall: String = "",
    val date: LocalDate = LocalDate(1970, 1, 1)
)