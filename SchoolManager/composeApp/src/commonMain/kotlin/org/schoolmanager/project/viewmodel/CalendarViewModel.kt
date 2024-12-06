package org.schoolmanager.project.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import org.schoolmanager.project.ApiService
import org.schoolmanager.project.data.model.Calendar



class CalendarViewModel : ViewModel() {
    private val coroutineScope= CoroutineScope(SupervisorJob() +Dispatchers.Default)
    val courses= MutableStateFlow<List<Calendar>>(emptyList())

    //FCT TO HAVE CALENDAR FROM THE API
    fun fetchCalendar() {
        coroutineScope.launch {
            val fetchedCalendar = ApiService.fetchCalendar()
            courses.value = fetchedCalendar
        }
    }





    fun getCoursesForDate(date: LocalDate): List<Calendar> {
        return courses.value.filter {LocalDate.parse(it.date)==date}
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
