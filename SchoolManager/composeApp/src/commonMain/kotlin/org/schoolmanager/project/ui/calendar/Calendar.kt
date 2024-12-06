package org.schoolmanager.project.ui.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.Icon
import androidx.compose.material.IconButton

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.schoolmanager.project.data.model.Course
import org.schoolmanager.project.viewmodel.CalendarViewModel
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.profilephoto
import kotlin.time.Duration.Companion.days
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.IntOffset
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import kotlinx.datetime.minus
import org.schoolmanager.project.data.model.Calendar
import org.schoolmanager.project.viewmodel.CoursesViewModel

@Composable
fun CalendarScreen(calendarViewModel: CalendarViewModel = CalendarViewModel(), coursesViewModel: CoursesViewModel = CoursesViewModel(), goToProfile:()-> Unit, GoToDetailsCourse: (Course) -> Unit) {

    val timeZone = TimeZone.currentSystemDefault()
    // Date sélectionnée par défaut
    var selectedDate by remember { mutableStateOf(Clock.System.now().toLocalDateTime(timeZone).date) }
    var isMonthView by remember { mutableStateOf(true) }
    // Récupérer le mois et l'année
    val month = selectedDate.month.name
    val year = selectedDate.year
    val daysInMonth = calendarViewModel.getDaysInMonth(selectedDate)
    // Calculer le premier jour du mois
    val firstDayOfWeek = LocalDate(selectedDate.year, selectedDate.month, 1).dayOfWeek
    val selectedDateInstant = selectedDate.atStartOfDayIn(timeZone)

    // Trouver le début de la semaine (lundi)
    val startOfWeekInstant = selectedDateInstant.minus(selectedDate.dayOfWeek.ordinal.days)
    val startOfWeek = startOfWeekInstant.toLocalDateTime(timeZone).date

    // Liste des dates de la semaine en ajoutant des jours à partir du début de la semaine
    val weekDates = (0 until 7).map { dayOffset ->
        startOfWeek.plus(dayOffset, DateTimeUnit.DAY)
    }
    val selectedDateCourses = calendarViewModel.getCoursesForDate(selectedDate)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 56.dp)  // Laisse de la place pour la barre de navigation en bas
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            // En-tête avec le mois/année et le bouton de bascule
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { isMonthView = !isMonthView }) {
                    if (isMonthView) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp, // Icône pour vue hebdomadaire
                            contentDescription = "Switch to weekly view"
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown, // Icône pour vue mensuelle
                            contentDescription = "Switch to monthly view"
                        )
                    }
                }
                Text(
                    text = "${month.lowercase()} $year",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter= painterResource(Res.drawable.profilephoto),
                    contentDescription= "ProfilePhoto",
                    modifier= Modifier
                        .clip(CircleShape)
                        .size(55.dp)
                        .clickable{goToProfile()},
                    contentScale= ContentScale.Crop
                )
            }
            Divider()
            Spacer(modifier = Modifier.height(5.dp))
            WeekDaysHeader(selectedDate = selectedDate, isMonthView = isMonthView, onMonthChanged = { newDate ->
                selectedDate = newDate
            },)
            if (isMonthView) {
                // Affichage de la grille des jours du mois
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f) // Réduction pour laisser des colonnes vides à gauche et droite
                        .align(Alignment.CenterHorizontally)
                ) {
                    MonthlyCalendar(
                        daysInMonth = daysInMonth,
                        firstDayOfWeek = firstDayOfWeek,
                        selectedDate = selectedDate,
                        onDayClick = { day ->
                            selectedDate = LocalDate(selectedDate.year, selectedDate.month, day)
                        }
                    )
                }
                // Divider entre le calendrier et la liste des cours
                Divider()
                Spacer(modifier = Modifier.height(13.dp))
                // Affichage des cours du jour sélectionné
                CourseList(selectedDateCourses, coursesViewModel, GoToDetailsCourse)
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f) // Réduction pour laisser des colonnes vides à gauche et droite
                        .align(Alignment.CenterHorizontally)
                ) {
                    // Affichage de la semaine courante avec les dates et les cours de chaque jour
                    WeeklyCalendar(weekDates, selectedDate) { date ->
                        selectedDate = date
                    }
                }
                // Divider entre le calendrier et la liste des cours
                Divider()
                Spacer(modifier = Modifier.height(13.dp))
                // Affichage des cours du jour sélectionné dans la vue hebdomadaire
                CourseList(selectedDateCourses, coursesViewModel, GoToDetailsCourse)
            }
        }
    }
}

@Composable
fun MonthlyCalendar(
    daysInMonth: List<Int>, // Liste des jours dans le mois (1, 2, ..., 31)
    firstDayOfWeek: DayOfWeek, // Le premier jour du mois (par ex., DayOfWeek.MONDAY)
    selectedDate: LocalDate, // Date actuellement sélectionnée
    onDayClick: (Int) -> Unit // Action lors du clic sur un jour
) {
    // Obtenir le décalage pour le premier jour
    val firstDayOffset = (firstDayOfWeek.ordinal + 7) % 7 // Décalage pour le premier jour
    val gridModifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()

    Column( modifier = Modifier.background(MaterialTheme.colors.background))  {
        Spacer(modifier = Modifier.height(8.dp))

        // Grille des jours
        LazyVerticalGrid(
            columns = GridCells.Fixed(7), // 7 colonnes pour les jours de la semaine
            modifier = gridModifier
        ) {
            // Espaces vides avant le début du mois
            items(firstDayOffset) {
                Box(modifier = Modifier.aspectRatio(1f)) // Case vide pour alignement
            }

            // Affichage des jours du mois
            items(daysInMonth.size) { index ->
                val day = daysInMonth[index]
                Box(
                    contentAlignment = Alignment.Center, // Centrer le texte
                    modifier = Modifier
                        .aspectRatio(1f) // Conserve un carré
                        .clickable { onDayClick(day) }
                ) {
                    Text(
                        text = day.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (day == selectedDate.dayOfMonth) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
                    )
                }
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
    // Taille uniforme pour les cellules (exemple : largeur de l'écran divisée par 7)
    val cellSize = 48.dp // Taille fixe pour chaque cellule (vous pouvez ajuster)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly // Espace uniforme entre les cellules
    ) {
        items(weekDates.size) { index ->
            val date = weekDates[index]
            Box(
                modifier = Modifier
                    .size(cellSize) // Taille fixe pour chaque cellule
                    .padding(4.dp) // Espacement autour des cellules
                    .clickable { onDateSelected(date) },
                contentAlignment = Alignment.Center // Centrer le texte dans chaque cellule
            ) {
                Text(
                    text = date.dayOfMonth.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (date == selectedDate) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center // Centrage du texte
                )
            }
        }
    }
}

@Composable
fun CourseList(courses: List<Calendar>, coursesViewModel: CoursesViewModel, GoToDetailsCourse: (Course) -> Unit) {
    LazyColumn {
        items(courses) { courseCalendar ->
            val course = coursesViewModel.getCourseById(courseCalendar.idcourse)
            course?.let {
                CourseItem(courseCalendar, it, GoToDetailsCourse)
            }
        }
        item{Spacer(modifier= Modifier.height(40.dp))}
    }
}

@Composable
fun CourseItem(calendar: Calendar, course: Course, GoToDetailsCourse: (Course) -> Unit) {
    var animatedOffset by remember { mutableStateOf(1000f) }

    LaunchedEffect(true) {
        // Animation à l'entrée depuis la gauche
        animatedOffset = 0f
    }

    val offsetAnimation by animateFloatAsState(
        targetValue = animatedOffset,
        animationSpec = tween(durationMillis = 500) // Durée de l'animation
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .offset { IntOffset(offsetAnimation.toInt(), 0) } // Animation du mouvement
            .clickable {GoToDetailsCourse(course)},
        elevation = 8.dp,
        shape= RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier= Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement= Arrangement.SpaceBetween,
            verticalAlignment= Alignment.CenterVertically
        )
        {
            Column{
                Row(verticalAlignment= Alignment.CenterVertically) {
                    course.image?.let { painterResource(it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "Course Image",
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(40.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier= Modifier.width(8.dp))
                    Text(
                        text = course.name ?: "Unknown Course",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,

                    )
                }
                Spacer(modifier= Modifier.height(4.dp))
                Text(
                    text = "${calendar.startTime} - ${calendar.endTime}",
                    fontSize = 16.sp,

                )
            }
            Column(horizontalAlignment= Alignment.End){
                Text(
                    text= "Room",
                    fontSize= 20.sp,
                    fontWeight= FontWeight.Bold,

                )
                Spacer(modifier= Modifier.height(4.dp))
                Text(
                    text= calendar.hall,
                    fontSize= 16.sp,
                )
            }
        }
    }
}

@Composable
fun WeekDaysHeader(selectedDate: LocalDate, isMonthView: Boolean, onMonthChanged: (LocalDate) -> Unit) {
    // Liste des jours de la semaine dans l'ordre
    val weekDays = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    // Trouver le jour de la semaine de la date sélectionnée (0 = Monday, etc.)
    val selectedDayOfWeekIndex = selectedDate.dayOfWeek.ordinal

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly // Distribution uniforme
    ) {
        IconButton(onClick = {
            // Aller au mois précédent
            val newSelectedMont = if (isMonthView) {
                selectedDate.minus(1, DateTimeUnit.MONTH)
            } else {
                selectedDate.minus(7, DateTimeUnit.DAY)
            }
            // Appeler la fonction onMonthChanged pour notifier le parent de la nouvelle date
            onMonthChanged(newSelectedMont)
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Previous month"
            )
        }
        weekDays.forEachIndexed { index, day ->
            Text(
                text = day,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.weight(1f, fill = true), // Chaque jour prend la même largeur
                textAlign = TextAlign.Center, // Alignement centré pour un meilleur alignement
                color = if (index == selectedDayOfWeekIndex) MaterialTheme.colors.primary  else MaterialTheme.colors.onBackground // Mettre en bleu si c'est le jour sélectionné
            )
        }
        IconButton(onClick = {
            // Aller au mois suivant
            val newSelectedMont = if (isMonthView) {
                selectedDate.plus(1, DateTimeUnit.MONTH)
            } else {
                selectedDate.plus(7, DateTimeUnit.DAY)
            }
            // Appeler la fonction onMonthChanged pour notifier le parent de la nouvelle date
            onMonthChanged(newSelectedMont)
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Next month"
            )
        }
    }
}

