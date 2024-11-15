package org.schoolmanager.project.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.datetime.LocalDate
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import org.schoolmanager.project.data.model.Course

class CalendarViewModel : ViewModel() {
    private val courses = listOf(
        Course("Elec", "12H", "15H", "EA13", LocalDate(2023, 3, 16)),
        Course("Math", "10H", "12H", "EA12", LocalDate(2023, 3, 16)),
        Course("Physics", "15H", "17H", "EA11", LocalDate(2023, 3, 17))
    )

    fun getCoursesForDate(date: LocalDate): List<Course> {
        return courses.filter { it.date == date }
    }

    fun getDaysInMonth(): List<String> {
        return (1..31).map { it.toString() } // Simplicité pour un mois de 31 jours
    }

    fun getWeekDates(startDate: LocalDate): List<LocalDate> {
        return (0 until 7).map { startDate.plus(it, DateTimeUnit.DAY) }
    }
}
