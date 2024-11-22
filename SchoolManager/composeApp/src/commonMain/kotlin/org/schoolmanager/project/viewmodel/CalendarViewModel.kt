package org.schoolmanager.project.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.datetime.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import org.schoolmanager.project.data.model.Course

class CalendarViewModel : ViewModel() {
    private val courses = listOf(
        Course("Elec", "12H", "15H", "EA13", LocalDate(2024, 11, 21)),
        Course("Math", "10H", "12H", "EA12", LocalDate(2024, 11, 21)),
        Course("Physics", "15H", "17H", "EA11", LocalDate(2024, 11, 21)),
        Course("Elec", "12H", "15H", "EA13", LocalDate(2024, 11, 22)),
        Course("Math", "10H", "12H", "EA12", LocalDate(2024, 11, 22)),
        Course("Physics", "15H", "17H", "EA11", LocalDate(2024, 11, 22)),
        Course("Elec", "12H", "15H", "EA13", LocalDate(2024, 11, 25)),
        Course("Math", "10H", "12H", "EA12", LocalDate(2024, 11, 25)),
        Course("Physics", "15H", "17H", "EA11", LocalDate(2024, 11, 25)),
    )

    fun getCoursesForDate(date: LocalDate): List<Course> {
        return courses.filter { it.date == date }
    }

    fun getDaysInMonth(selectedDate: LocalDate): List<Int> {
        val year = selectedDate.year
        val month = selectedDate.month
        val daysInMonth = getLengthOfMonth(year, month) // Appelle une fonction pour calculer le nombre de jours
        return (1..daysInMonth).toList()

    }

    private fun getLengthOfMonth(year: Int, month: Month): Int {
        return when (month) {
            Month.JANUARY, Month.MARCH, Month.MAY, Month.JULY, Month.AUGUST, Month.OCTOBER, Month.DECEMBER -> 31
            Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> 30
            Month.FEBRUARY -> if (isLeapYear(year)) 29 else 28
            // Ajoutez une branche "else" pour satisfaire le compilateur
            else -> throw IllegalArgumentException("Mois inattendu : $month")
        }
    }

    private fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }

    fun getWeekDates(startDate: LocalDate): List<LocalDate> {
        return (0 until 7).map { startDate.plus(it, DateTimeUnit.DAY) }
    }
}
