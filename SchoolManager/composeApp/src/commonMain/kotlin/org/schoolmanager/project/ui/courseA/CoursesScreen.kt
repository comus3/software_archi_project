package org.schoolmanager.project.ui.courseA

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.addcourse
import schoolmanager.composeapp.generated.resources.profilephoto
import schoolmanager.composeapp.generated.resources.shopping_cart
import schoolmanager.composeapp.generated.resources.alternatif_monophase
import schoolmanager.composeapp.generated.resources.electronic_circuit
import schoolmanager.composeapp.generated.resources.motor
import schoolmanager.composeapp.generated.resources.administration_reseau



@Composable
fun CoursesScreen(
    GoToAddCourse: () -> Unit,
    GoToCourseDetail: () -> Unit,
    GoToProfile: () -> Unit,
    GoToSyllabus: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("Hello") }
    // Liste filtrée des cours
    val filteredCourses = getCourseList(GoToCourseDetail).filter { course ->
        course.title.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Fill the available size
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = "COURSE A",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(Res.drawable.profilephoto),
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .clip(CircleShape)
                    .size(65.dp)
                    .clickable { GoToProfile() },
                contentScale = ContentScale.Crop
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {searchQuery = it},
                label = { Text("Course") },
                placeholder = { Text("Search here...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                modifier = Modifier
                    .weight(1f)
            )
            Image(
                painter = painterResource(Res.drawable.addcourse),
                contentDescription = "Settings",
                modifier = Modifier
                    .size(50.dp)
                    .clickable { GoToAddCourse() }
                    .align(Alignment.CenterVertically)
            )
            Button(
                onClick = { GoToSyllabus() },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(red = 62, green = 96, blue = 160),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .height(40.dp)
                    .wrapContentWidth(),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, // Aligne l'icône et le texte
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // Espace entre l'icône et le texte
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.shopping_cart), // Remplace par ton icône
                        contentDescription = "Syllabus Icon",
                        modifier = Modifier.size(20.dp) // Taille de l'icône
                    )
                    Text("Syllabus") // Texte du bouton
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredCourses) { course ->
                CourseCard(
                    title = course.title,
                    resource = course.imageResId,
                    GoToCourseDetail = course.onClick
                )
            }
        }
    }
}

@Composable
fun CourseCard(title: String, resource: DrawableResource, GoToCourseDetail: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable { GoToCourseDetail() },
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Course image
            Image(
                painter = painterResource(resource),
                contentDescription = "$title Icon",
                modifier = Modifier.size(40.dp)
            )
            // Course title
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

data class Course(
    val title: String,
    val imageResId: DrawableResource,
    val onClick: () -> Unit
)

fun getCourseList(GoToCourseDetail: () -> Unit): List<Course> {
    return listOf(
        Course("Elec Q1", Res.drawable.electronic_circuit, GoToCourseDetail),
        Course("Elec Q2", Res.drawable.alternatif_monophase, GoToCourseDetail),
        Course("Motors", Res.drawable.motor, GoToCourseDetail),
        Course("Network", Res.drawable.administration_reseau, GoToCourseDetail),
        Course("Elec Q1", Res.drawable.electronic_circuit, GoToCourseDetail),
        Course("Elec Q2", Res.drawable.alternatif_monophase, GoToCourseDetail),
        Course("Motors", Res.drawable.motor, GoToCourseDetail),
        Course("Network", Res.drawable.administration_reseau, GoToCourseDetail),
        Course("Elec Q1", Res.drawable.electronic_circuit, GoToCourseDetail),
        Course("Elec Q2", Res.drawable.alternatif_monophase, GoToCourseDetail),
        Course("Motors", Res.drawable.motor, GoToCourseDetail),
        Course("Network", Res.drawable.administration_reseau, GoToCourseDetail),
    )
}