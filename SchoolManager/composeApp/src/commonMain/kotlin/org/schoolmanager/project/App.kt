package org.schoolmanager.project
/*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

// Simuler des données de cours
data class Course(val name: String, val startTime: String, val endTime: String, val day: String)

val sampleCourses = listOf(
    Course("Mathematics", "08:00 AM", "10:00 AM", "Monday"),
    Course("History", "10:30 AM", "12:30 PM", "Monday"),
    Course("Physics", "01:00 PM", "03:00 PM", "Tuesday"),
    Course("Chemistry", "08:00 AM", "10:00 AM", "Wednesday"),
    Course("Biology", "11:00 AM", "01:00 PM", "Friday"),
    Course("English", "02:00 PM", "04:00 PM", "Friday")
)

@Composable
fun App() {
    MaterialTheme {
        var viewType by remember { mutableStateOf(ViewType.Day) }
        val selectedDay by remember {
            mutableStateOf(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date)
        }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            // Choisir la vue (jour ou semaine)
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { viewType = ViewType.Day }) {
                    Text("Day View")
                }
                Button(onClick = { viewType = ViewType.Week }) {
                    Text("Week View")
                }
            }

            // Affichage selon le type de vue (jour ou semaine)
            if (viewType == ViewType.Day) {
                DayView(selectedDay = selectedDay)
            } else {
                WeekView(selectedDay = selectedDay)
            }
        }
    }
}

@Composable
fun DayView(selectedDay: LocalDate) {
    val coursesForDay = sampleCourses.filter { it.day.equals(selectedDay.dayOfWeek.name, ignoreCase = true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Text("Courses for $selectedDay", style = MaterialTheme.typography.h6)

        LazyColumn {
            items(coursesForDay) { course ->
                CourseItem(course)
            }
        }
    }
}

@Composable
fun WeekView(selectedDay: LocalDate) {
    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
    var selectedWeekDay by remember { mutableStateOf(selectedDay.dayOfWeek.name) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Text("Courses for the Week", style = MaterialTheme.typography.h6)

        // Afficher les jours de la semaine
        LazyRow {
            items(daysOfWeek) { day ->
                Button(
                    onClick = { selectedWeekDay = day },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(day)
                }
            }
        }

        // Afficher les cours pour le jour sélectionné de la semaine
        val coursesForSelectedDay = sampleCourses.filter { it.day.equals(selectedWeekDay, ignoreCase = true) }
        LazyColumn {
            items(coursesForSelectedDay) { course ->
                CourseItem(course)
            }
        }
    }
}

@Composable
fun CourseItem(course: Course) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(course.name, style = MaterialTheme.typography.h6)
            Text("Time: ${course.startTime} - ${course.endTime}", style = MaterialTheme.typography.body2)
        }
    }
}

enum class ViewType {
    Day,
    Week
}*/

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.CircleShape
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.days

// Classe modèle pour les cours
data class Course(
    val name: String,
    val startTime: String,
    val endTime: String,
    val hall: String,
    val date: LocalDate
)

// ViewModel simplifié pour stocker et fournir les données
class CalendarViewModel {
    private val courses = listOf(
        Course("Elec", "12H", "15H", "EA13", LocalDate(2023, 3, 16)),
        Course("Math", "10H", "12H", "EA12", LocalDate(2023, 3, 16)),
        Course("Physics", "15H", "17H", "EA11", LocalDate(2023, 3, 17))
        // Ajouter d'autres cours pour le mois
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
        BottomNavigationBar(modifier = Modifier.align(Alignment.BottomCenter))
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
}

@Composable
fun App() {
    MaterialTheme {
        CalendarScreen()
    }
}
