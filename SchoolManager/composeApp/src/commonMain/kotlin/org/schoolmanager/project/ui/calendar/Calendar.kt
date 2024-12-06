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
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.schoolmanager.project.data.model.Course
import org.schoolmanager.project.viewmodel.CalendarViewModel
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.profilephoto
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.IntOffset
import androidx.compose.animation.core.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import kotlinx.datetime.minus


@Composable
fun CalendarScreen(viewModel: CalendarViewModel = CalendarViewModel(), goToProfile:()-> Unit) {
    // Obtenir la date actuelle
    val timeZone = TimeZone.currentSystemDefault()
    var selectedDate by remember { mutableStateOf(Clock.System.now().toLocalDateTime(timeZone).date) }
    var isMonthView by remember { mutableStateOf(true) }

    // Calcul du mois et de l'année dynamiquement à partir de selectedDate
    val month = selectedDate.month.name
    val year = selectedDate.year

    // Calcul des jours pour le mois ou la semaine
    val daysInMonth = viewModel.getDaysInMonth(selectedDate)
    val firstDayOfWeek = LocalDate(selectedDate.year, selectedDate.month, 1).dayOfWeek

    //val startOfWeek = selectedDate.minusDays(selectedDate.dayOfWeek.ordinal.toLong())
    val startOfWeek = selectedDate.minus(selectedDate.dayOfWeek.ordinal, DateTimeUnit.DAY)
    //val weekDates = (0 until 7).map { startOfWeek.plusDays(it.toLong()) }
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
            // En-tête avec les flèches de navigation et bascule vue
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
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
                    fontWeight = FontWeight.Bold
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

            // Afficher les jours de la semaine
            WeekDaysHeader(selectedDate = selectedDate,onMonthChanged = { newDate ->
                selectedDate = newDate
            },)

            // Affichage en fonction de la vue sélectionnée (mois ou semaine)
            if (isMonthView) {
                MonthlyCalendar(
                    daysInMonth = daysInMonth,
                    firstDayOfWeek = firstDayOfWeek,
                    selectedDate = selectedDate,
                    onDayClick = { day ->
                        selectedDate = LocalDate(selectedDate.year, selectedDate.month, day)
                    }
                )
                val courses = viewModel.getCoursesForDate(selectedDate)
                CourseList(courses)
            } else {
                WeeklyCalendar(weekDates, selectedDate) { date ->
                    selectedDate = date
                }

                val courses = viewModel.getCoursesForDate(selectedDate)
                CourseList(courses)
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

    Column {
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
                        color = if (day == selectedDate.dayOfMonth) Color.Blue else Color.Black
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
                    color = if (date == selectedDate) Color.Blue else Color.Black,
                    textAlign = TextAlign.Center // Centrage du texte
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
    var animatedOffset by remember { mutableStateOf(1000f) }

    LaunchedEffect(true) {
        // Animation à l'entrée depuis la gauche
        animatedOffset = 0f
    }

    val offsetAnimation by animateFloatAsState(
        targetValue = animatedOffset,
        animationSpec = tween(durationMillis = 1000) // Durée de l'animation
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .offset { IntOffset(offsetAnimation.toInt(), 0) }, // Animation du mouvement
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
                Text(
                    text= course.name,
                    fontSize= 24.sp,
                    fontWeight= FontWeight.Bold,
                    color= Color.Black
                )
                Spacer(modifier= Modifier.height(4.dp))
                Text(
                    text= "${course.startTime} - ${course.endTime}",
                    fontSize= 16.sp,
                    color= Color.Black
                )
            }
            Column(horizontalAlignment= Alignment.End){
                Text(
                    text= "Room",
                    fontSize= 20.sp,
                    fontWeight= FontWeight.Bold,
                    color= Color.Black
                )
                Spacer(modifier= Modifier.height(4.dp))
                Text(
                    text= course.hall,
                    fontSize= 16.sp,
                )
            }
        }
    }
}

@Composable
fun WeekDaysHeader(selectedDate: LocalDate,onMonthChanged: (LocalDate) -> Unit) {
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
                    val newSelectedMont = selectedDate.minus(1, DateTimeUnit.MONTH)
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
                color = if (index == selectedDayOfWeekIndex) Color.Blue else Color.Black // Mettre en bleu si c'est le jour sélectionné
            )
        }
        IconButton(onClick = {
            // Aller au mois suivant
            val newSelectedMont = selectedDate.plus(1, DateTimeUnit.MONTH)
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

