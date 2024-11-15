package org.schoolmanager.project

import ContactDetailScreen
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.painterResource
import org.schoolmanager.project.ui.calendar.CalendarScreen
import org.schoolmanager.project.ui.contacts.ContactsScreen
import org.schoolmanager.project.ui.courses.CoursesScreen
import org.schoolmanager.project.ui.addcourse.AddCourseScreen
import org.schoolmanager.project.ui.detailscourse.CourseDetailsScreen
import org.schoolmanager.project.ui.grades.GradesScreen
import org.schoolmanager.project.ui.homepage.HomePage
import org.schoolmanager.project.ui.profile.ProfileScreen
import org.schoolmanager.project.ui.settings.SettingsScreen
import org.schoolmanager.project.viewmodel.ContactsViewModel
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.iconhome
import schoolmanager.composeapp.generated.resources.iconcalendar
import schoolmanager.composeapp.generated.resources.iconcourses
import schoolmanager.composeapp.generated.resources.iconcontact


@Composable
@Preview
fun App(){
    MaterialTheme{
        //SELECTED PAGE
        var SelectedScreen by remember {mutableStateOf("Home")}
        val viewModel= ContactsViewModel()

        //NAVIGATION BAR
        Scaffold(
            bottomBar= {
                BottomNavigation(backgroundColor= Color(red=62,green=96,blue=160), contentColor= Color.White){
                    //HOME
                    BottomNavigationItem(
                        icon= {Icon(painter= painterResource(Res.drawable.iconhome), contentDescription= "Home")},
                        label= {Text("Home")},
                        selected= SelectedScreen=="Home",
                        onClick= {SelectedScreen= "Home"},
                    )
                    //CALENDAR
                    BottomNavigationItem(
                        icon= {Icon(painter= painterResource(Res.drawable.iconcalendar), contentDescription= "Calendar")},
                        label= {Text("Calendar")},
                        selected= SelectedScreen=="Calendar",
                        onClick= {SelectedScreen= "Calendar"}
                    )
                    //COURSES
                    BottomNavigationItem(
                        icon= {Icon(painter= painterResource(Res.drawable.iconcourses), contentDescription= "Courses")},
                        label= {Text("Courses")},
                        selected= SelectedScreen=="Courses",
                        onClick= {SelectedScreen= "Courses"}
                    )
                    //CONTACT
                    BottomNavigationItem(
                        icon= {Icon(painter= painterResource(Res.drawable.iconcontact), contentDescription= "Contact")},
                        label= {Text("Contact")},
                        selected= SelectedScreen=="Contact",
                        onClick= {SelectedScreen= "Contact"}
                    )
                }
            }
        )

        //SCREENS MANAGEMENT
        {when (SelectedScreen){
            "Home"-> HomePage(
                GoToProfile = {SelectedScreen= "Profile"}).Content()
            "Profile"-> ProfileScreen(
                BackHomePage= {SelectedScreen= "Home"},
                GoToSettings= {SelectedScreen= "Settings"},
                GoToGrades= {SelectedScreen= "Grades"})
            "Settings"-> SettingsScreen(
                BackProfile= {SelectedScreen= "Profile"})
            "Grades"-> GradesScreen(
                BackProfile= {SelectedScreen= "Profile"})
            "Calendar"-> CalendarScreen()
            "Courses"-> CoursesScreen(
                GoToAddCourse= {SelectedScreen= "AddCourse"},
                GoToCourseDetail= {SelectedScreen= "DetailsCourse"})
            "DetailsCourse"-> CourseDetailsScreen(
                BackCourses= {SelectedScreen= "Courses"})
            "AddCourse"-> AddCourseScreen(
                BackCourses= {SelectedScreen= "Courses"})
            "Contact"-> ContactsScreen(
                viewModel= viewModel,
                GoToContactDetailScreen= {SelectedScreen= "DetailContact"})
            "DetailContact"-> ContactDetailScreen(
                contact= viewModel.selectedContact.value,
                onBack= {SelectedScreen= "Contact"})
        }}
    }
}