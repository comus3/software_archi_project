package org.schoolmanager.project

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import schoolmanager.composeapp.generated.resources.compose_multiplatform

import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.iconhome
import schoolmanager.composeapp.generated.resources.iconcalendar
import schoolmanager.composeapp.generated.resources.iconcourses
import schoolmanager.composeapp.generated.resources.iconcontact


@Composable
@Preview
fun App(){
    MaterialTheme{
        //HOMEPAGE
        HomePage().Content()

        //NAVIGATION BAR
        var SelectedScreen by remember {mutableStateOf("Home")}
        Scaffold(
            bottomBar= {
                BottomNavigation{
                    //HOME
                    BottomNavigationItem(
                        icon= {Icon(painter= painterResource(Res.drawable.iconhome), contentDescription= "Home")},
                        label= {Text("Home")},
                        selected= SelectedScreen=="Home",
                        onClick= {SelectedScreen= "Home"}
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
        {when (SelectedScreen){
            "Home"-> HomePage().Content()
            "Calendar"-> Page1().Content()
            "Courses"-> Page1().Content()
            "Contact"-> Page1().Content()
        }}
    }
}
