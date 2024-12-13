package org.schoolmanager.project

import ContactDetailScreen
import MyTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.painterResource
import org.schoolmanager.project.data.model.Course
import org.schoolmanager.project.data.model.NewsHomePage
import org.schoolmanager.project.ui.Login.LoginScreen
import org.schoolmanager.project.ui.homepage.HomePageScreen
import org.schoolmanager.project.ui.calendar.CalendarScreen
import org.schoolmanager.project.ui.contacts.ContactsScreen
import org.schoolmanager.project.ui.courseA.CoursesScreen
import org.schoolmanager.project.ui.courseB.AddCourseScreen
import org.schoolmanager.project.ui.courseC.CourseDetailsScreen
import org.schoolmanager.project.ui.grades.GradesScreen
import org.schoolmanager.project.ui.homepage.HomePageDetailsNews
import org.schoolmanager.project.ui.profile.ProfileScreen
import org.schoolmanager.project.ui.settings.AboutScreen
import org.schoolmanager.project.ui.settings.LanguageScreen
import org.schoolmanager.project.ui.settings.SettingsScreen
import org.schoolmanager.project.ui.settings.TermsScreen
import org.schoolmanager.project.ui.syllabus.HomeSyllabusScreen
import org.schoolmanager.project.viewmodel.CalendarViewModel
import org.schoolmanager.project.viewmodel.NewsViewModel
import org.schoolmanager.project.viewmodel.SyllabusViewModel
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.iconhome
import schoolmanager.composeapp.generated.resources.iconcalendar
import schoolmanager.composeapp.generated.resources.iconcourses
import schoolmanager.composeapp.generated.resources.iconcontact

@Composable
@Preview
fun App() {
    // Global theme state
    var isDarkModeEnabled by remember { mutableStateOf(false) }

    // Login state
    var isLoggedIn by remember { mutableStateOf(false) }

    // Selected screen state
    var SelectedScreen by remember { mutableStateOf("Home") }
    val viewModel = ContactsViewModel()
    var SelectedButton by remember { mutableStateOf("Today classes") }
    var SelectedCourse: Course? by remember { mutableStateOf(null) }
    var SelectedNews: NewsHomePage? by remember { mutableStateOf(null) }
    var SelectedOrientation: Orientation? by remember { mutableStateOf(null) }
    var ScreenHistory = remember { mutableStateListOf<String>() }

    // Global theme wrapper
    MyTheme(darkTheme = isDarkModeEnabled) {
        if (!isLoggedIn) {
            // Login Page
            LoginScreen(
                onLoginSuccess = { isLoggedIn = true }, // Set isLoggedIn to true after successful login
                viewModel = viewModel
            )
        } else {
            // Main App with Bottom Navigation
            Scaffold(
                bottomBar = {
                    // Only show BottomNavigation if the user is logged in and not on Login page
                    if (SelectedScreen != "Login") {
                        BottomNavigation(
                            backgroundColor = Color(red = 62, green = 96, blue = 160),
                            contentColor = if (isDarkModeEnabled) Color.White else Color.Black
                        ) {
                            // HOME
                            BottomNavigationItem(
                                icon = { Icon(painter = painterResource(Res.drawable.iconhome), contentDescription = "Home") },
                                label = { Text("Home") },
                                selected = SelectedScreen == "Home",
                                onClick = { SelectedScreen = "Home"; ScreenHistory.add("Home") },
                            )
                            // CALENDAR
                            BottomNavigationItem(
                                icon = { Icon(painter = painterResource(Res.drawable.iconcalendar), contentDescription = "Calendar") },
                                label = { Text("Calendar") },
                                selected = SelectedScreen == "Calendar",
                                onClick = { SelectedScreen = "Calendar"; ScreenHistory.add("Calendar") }
                            )
                            // COURSES
                            BottomNavigationItem(
                                icon = { Icon(painter = painterResource(Res.drawable.iconcourses), contentDescription = "Courses") },
                                label = { Text("Courses") },
                                selected = SelectedScreen == "Courses",
                                onClick = { SelectedScreen = "Courses"; ScreenHistory.add("Courses") }
                            )
                            // CONTACT
                            BottomNavigationItem(
                                icon = { Icon(painter = painterResource(Res.drawable.iconcontact), contentDescription = "Contact") },
                                label = { Text("Contact") },
                                selected = SelectedScreen == "Contact",
                                onClick = { SelectedScreen = "Contact"; ScreenHistory.add("Contact") }
                            )
                        }
                    }
                }
            ) { // Screens Management
                when (SelectedScreen) {
                    "Home" -> HomePageScreen(
                        SelectedButton = SelectedButton,
                        GoToProfile = { SelectedScreen = "Profile"; ScreenHistory.add("Home") },
                        newsViewModel = NewsViewModel(),
                        calendarViewModel = CalendarViewModel(),
                        GoToDetailsCourse = { course ->
                            SelectedCourse = course
                            SelectedScreen = "DetailsCourse"
                            ScreenHistory.add("Home")
                        },
                        GoToDetailsNews = { news ->
                            SelectedNews = news
                            SelectedScreen = "DetailsNews"
                            ScreenHistory.add("Home")
                        }
                    )
                    "DetailsNews" -> SelectedNews?.let {
                        HomePageDetailsNews(it, BackHomePage = { button ->
                            SelectedButton = button
                            SelectedScreen = "Home"
                        })
                    }
                    "Profile" -> ProfileScreen(
                        BackHomePage = {
                            if (ScreenHistory.isNotEmpty()) {
                                ScreenHistory.removeAt(ScreenHistory.lastIndex)
                                SelectedScreen = ScreenHistory.lastOrNull() ?: "Home"
                            }
                        },
                        GoToSettings = { SelectedScreen = "Settings" },
                        GoToGrades = { SelectedScreen = "Grades" }
                    )
                    "Settings" -> SettingsScreen(
                        BackProfile = { SelectedScreen = "Profile" },
                        GoToLanguage = { SelectedScreen = "Language" },
                        GoToAbout = { SelectedScreen = "About" },
                        GoToTerms = { SelectedScreen = "Terms" },
                        onDarkModeToggle = { isDarkModeEnabled = it }
                    )
                    "Grades" -> GradesScreen(BackProfile = { SelectedScreen = "Profile" })
                    "Calendar" -> CalendarScreen(
                        goToProfile = { SelectedScreen = "Profile"; ScreenHistory.add("Calendar") },
                        GoToDetailsCourse = { course ->
                            SelectedCourse = course
                            SelectedScreen = "DetailsCourse"
                            ScreenHistory.add("Calendar")
                        }
                    )
                    "Courses" -> CoursesScreen(
                        GoToAddCourse = { SelectedScreen = "AddCourse"; ScreenHistory.add("Courses") },
                        GoToCourseDetail = { SelectedScreen = "DetailsCourse"; ScreenHistory.add("Courses") },
                        GoToProfile = { SelectedScreen = "Profile"; ScreenHistory.add("Courses") },
                        GoToSyllabus = { SelectedScreen = "HomeSyllabus"; ScreenHistory.add("Courses") }
                    )
                    "DetailsCourse" -> SelectedCourse?.let { course ->
                        CourseDetailsScreen(course = course, BackCourses = {
                            if (ScreenHistory.isNotEmpty()) {
                                ScreenHistory.removeAt(ScreenHistory.lastIndex)
                                SelectedScreen = ScreenHistory.lastOrNull() ?: "Home"
                            }
                        })
                    }
                    "HomeSyllabus" -> HomeSyllabusScreen(
                        BackCourse = {SelectedScreen = "Courses"},
                        GoToCart = {ScreenHistory.add("HomeSyllabus"); SelectedScreen = "CartSyllabus"},
                        GoToSyllabus = {orientation ->
                            SelectedOrientation = orientation
                            SelectedScreen = "ListSyllabus"
                        },
                        syllabusviewModel = SyllabusViewModel())
                    "ListSyllabus" -> SelectedOrientation?.let { orientation ->
                        SyllabusScreen(
                            BackCourse = { SelectedScreen = "HomeSyllabus" },
                            GoToCart = { SelectedScreen = "CartSyllabus" },
                            orientation = orientation,
                            syllabusviewModel = SyllabusViewModel()
                        )
                    }
                    "CartSyllabus" -> CartSyllabusScreen(
                        BackHomeSyllabus = { SelectedScreen = "HomeSyllabus" },
                        syllabusviewModel = SyllabusViewModel()
                    )
                    "AddCourse" -> AddCourseScreen(BackCourses = { SelectedScreen = "Courses"; ScreenHistory.add("AddCourse") })
                    "Contact" -> ContactsScreen(
                        viewModel = viewModel,
                        GoToContactDetailScreen = { SelectedScreen = "DetailContact"; ScreenHistory.add("Contact") },
                        GoToProfile = { SelectedScreen = "Profile"; ScreenHistory.add("Contact") }
                    )
                    "DetailContact" -> ContactDetailScreen(
                        contact = viewModel.selectedContact.value,
                        onBack = { SelectedScreen = "Contact" }
                    )
                    "Language" -> LanguageScreen(BackSettings = { SelectedScreen = "Settings" })
                    "About" -> AboutScreen(BackSettings = { SelectedScreen = "Settings" })
                    "Terms" -> TermsScreen(BackSettings = { SelectedScreen = "Settings" })
                }
            }
    }        }

}
