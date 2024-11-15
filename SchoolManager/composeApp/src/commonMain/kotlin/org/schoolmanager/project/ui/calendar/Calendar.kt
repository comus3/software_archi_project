package org.schoolmanager.project.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.CircleShape
import kotlinx.datetime.LocalDate
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import org.schoolmanager.project.data.model.Course
import org.schoolmanager.project.viewmodel.CalendarViewModel
import kotlin.time.Duration.Companion.days

@Composable
fun CalendarScreen(viewModel: CalendarViewModel = CalendarViewModel()) {
    var isMonthView by remember { mutableStateOf(true) }
    var selectedDate by remember { mutableStateOf(LocalDate(2023, 3, 16)) } // Date sélectionnée par défaut
    val daysInMonth = viewModel.getDaysInMonth()
    val timeZone = TimeZone.currentSystemDefault()
    val selectedDateInstant = selectedDate.atStartOfDayIn(timeZone)

    // Trouver le début de la semaine (lundi)
    val startOfWeekInstant = selectedDateInstant.minus(selectedDate.dayOfWeek.ordinal.days)
    val startOfWeek = startOfWeekInstant.toLocalDateTime(timeZone).date

    // Liste des dates de la semaine en ajoutant des jours à partir du début de la semaine
    val weekDates = (0 until 7).map { dayOffset ->
        startOfWeek.plus(dayOffset, DateTimeUnit.DAY)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 56.dp)  // Laisse de la place pour la barre de navigation en bas
                .fillMaxSize()
        ) {
            // En-tête avec le mois/année et le bouton de bascule
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "March 2023",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { isMonthView = !isMonthView }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = if (isMonthView) "Switch to Week View" else "Switch to Month View"
                    )
                }
            }

            if (isMonthView) {
                // Affichage de la grille de jours du mois
                MonthlyCalendar(daysInMonth, selectedDate.dayOfMonth.toString()) { day ->
                    selectedDate = LocalDate(selectedDate.year, selectedDate.month, day.toInt())

                }

                // Affichage des cours du jour sélectionné
                val courses = viewModel.getCoursesForDate(selectedDate)
                CourseList(courses)
            } else {
                // Affichage de la semaine courante avec les dates et les cours de chaque jour
                WeeklyCalendar(weekDates, selectedDate) { date ->
                    selectedDate = date
                }

                // Affichage des cours du jour sélectionné dans la vue hebdomadaire
                val courses = viewModel.getCoursesForDate(selectedDate)
                CourseList(courses)
            }
        }

        // Barre de navigation en bas
        //BottomNavigationBar(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun MonthlyCalendar(
    daysInMonth: List<String>,
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(daysInMonth.size) { index ->
            val day = daysInMonth[index]
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .background(
                        color = if (day == selectedDate) Color.Blue else Color.Transparent,
                        shape = CircleShape
                    )
                    .clickable { onDateSelected(day) },
                contentAlignment = Alignment.Center
            ) {
                Text(text = day, color = if (day == selectedDate) Color.White else Color.Black)
            }
        }
    }
}

@Composable
fun WeeklyCalendar(
    weekDates: List<LocalDate>,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(weekDates.size) { index ->
            val date = weekDates[index]
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onDateSelected(date) }
            ) {
                Text(
                    text = date.dayOfMonth.toString(),
                    color = if (date == selectedDate) Color.Blue else Color.Black
                )
            }
        }
    }
}

@Composable
fun CourseList(courses: List<Course>) {
    LazyColumn {
        items(courses) { course ->
            CourseItem(course)
        }
    }
}

@Composable
fun CourseItem(course: Course) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = course.name, fontWeight = FontWeight.Bold)
            Text(text = "${course.startTime} - ${course.endTime}")
            Text(text = "Room: ${course.hall}")
        }
    }
}
/*
@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
    BottomAppBar(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = Color(0xFF3F51B5)
    ) {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = { /* Naviguer à Home */ }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.DateRange, contentDescription = "Agenda") },
            label = { Text("Agenda") },
            selected = true,
            onClick = { /* Naviguer à Agenda */ }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Courses") },
            label = { Text("Courses") },
            selected = false,
            onClick = { /* Naviguer à Courses */ }
        )
    }
}*/