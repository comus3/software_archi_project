package org.schoolmanager.project.data.model
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.DrawableResource

//DATA COURSE CALENDAR
class CourseCalendar(
    val idcourse: Int,
    val startTime: String = "",
    val endTime: String = "",
    val hall: String = "",
    val date: LocalDate = LocalDate(1970, 1, 1)
)