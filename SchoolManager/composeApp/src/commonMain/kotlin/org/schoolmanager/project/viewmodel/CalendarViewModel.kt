package org.schoolmanager.project.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.datetime.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import org.schoolmanager.project.data.model.CourseCalendar


class CalendarViewModel : ViewModel() {
    //1= Electric
    //2= Motors
    //3= Math
    //4= Network
    private val courses = listOf(
        CourseCalendar(1, "12H", "15H", "EA13", LocalDate(2024, 11, 21)),
        CourseCalendar(2, "16H", "18H", "EA14", LocalDate(2024, 11, 21)),
        CourseCalendar(3, "10H", "12H", "EA12", LocalDate(2024, 11, 21)),
        CourseCalendar(4, "15H", "17H", "EA11", LocalDate(2024, 11, 21)),
        CourseCalendar(1, "10H", "12H", "EA13", LocalDate(2024, 11, 22)),
        CourseCalendar(3, "15H", "17H", "EA12", LocalDate(2024, 11, 22)),
        CourseCalendar(4, "18H", "19H", "EA11", LocalDate(2024, 11, 22)),
        CourseCalendar(3, "10H", "12H", "EA12", LocalDate(2024, 11, 24)),
        CourseCalendar(1, "12H", "15H", "EA13", LocalDate(2024, 11, 25)),
        CourseCalendar(2, "16H", "18H", "EA14", LocalDate(2024, 11, 25)),
        CourseCalendar(4, "11H", "13H", "EA11", LocalDate(2024, 11, 26)),
        CourseCalendar(4, "14H", "16H", "EA11", LocalDate(2024, 11, 26)),
        CourseCalendar(1, "17H", "18H", "EA13", LocalDate(2024, 11, 26)),
        CourseCalendar(1, "10H", "12H", "EA14", LocalDate(2024, 11, 28)),
        CourseCalendar(3, "15H", "16H", "EA12", LocalDate(2024, 11, 28)),
        CourseCalendar(4, "17H", "19H", "EA11", LocalDate(2024, 11, 28)),
        CourseCalendar(2, "19H", "21H", "EA13", LocalDate(2024, 11, 28)),
    )

    fun getCoursesForDate(date: LocalDate): List<CourseCalendar> {
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
