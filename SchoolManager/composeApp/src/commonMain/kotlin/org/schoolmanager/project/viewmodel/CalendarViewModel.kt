package org.schoolmanager.project.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.datetime.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import org.schoolmanager.project.data.model.Course
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.administration_reseau
import schoolmanager.composeapp.generated.resources.electronic_circuit
import schoolmanager.composeapp.generated.resources.alternatif_monophase
import schoolmanager.composeapp.generated.resources.motor


class CalendarViewModel : ViewModel() {
     val courses = listOf(
        Course("Elec", Res.drawable.alternatif_monophase,"12H", "15H", "EA13", LocalDate(2024, 11, 21)),
        Course("Motors", Res.drawable.motor,"16H", "18H", "EA14", LocalDate(2024, 11, 21)),
        Course("Math", Res.drawable.electronic_circuit,"10H", "12H", "EA12", LocalDate(2024, 11, 21)),
        Course("Network", Res.drawable.administration_reseau,"15H", "17H", "EA11", LocalDate(2024, 11, 21)),
        Course("Elec", Res.drawable.alternatif_monophase,"10H", "12H", "EA13", LocalDate(2024, 11, 22)),
        Course("Math", Res.drawable.electronic_circuit, "15H", "17H", "EA12", LocalDate(2024, 11, 22)),
        Course("Network",Res.drawable.administration_reseau, "18H", "19H", "EA11", LocalDate(2024, 11, 22)),
         Course("Math", Res.drawable.electronic_circuit, "10H", "12H", "EA12", LocalDate(2024, 11, 24)),
        Course("Elec", Res.drawable.alternatif_monophase,  "12H", "15H", "EA13", LocalDate(2024, 11, 25)),
        Course("Motors", Res.drawable.motor,"16H", "18H", "EA14", LocalDate(2024, 11, 25)),
        Course("Network", Res.drawable.administration_reseau,"11H", "13H", "EA11", LocalDate(2024, 11, 26)),
        Course("Network", Res.drawable.administration_reseau,"14H", "16H", "EA11", LocalDate(2024, 11, 26)),
        Course("Elec", Res.drawable.alternatif_monophase, "16H30", "18H", "EA13", LocalDate(2024, 11, 26)),
        Course("Motors", Res.drawable.motor,"10H", "12H", "EA14", LocalDate(2024, 11, 27)),
        Course("Math", Res.drawable.electronic_circuit,"15H", "16H", "EA12", LocalDate(2024, 11, 27)),
        Course("Network", Res.drawable.administration_reseau, "17H", "19H", "EA11", LocalDate(2024, 11, 27)),
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
