package org.schoolmanager.project.ui.courseB

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
import schoolmanager.composeapp.generated.resources.administration_reseau
import schoolmanager.composeapp.generated.resources.alternatif_monophase
import schoolmanager.composeapp.generated.resources.back
import schoolmanager.composeapp.generated.resources.electronic_circuit
import schoolmanager.composeapp.generated.resources.motor
import schoolmanager.composeapp.generated.resources.profilephoto
import schoolmanager.composeapp.generated.resources.shopping_cart
import org.schoolmanager.project.viewmodel.CoursesViewModel


@Composable
fun AddCourseScreen(
    viewModel: CoursesViewModel = CoursesViewModel(),
    BackCourses: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredCourses = viewModel.getAllCourses().filter { course ->
        course.name.contains(searchQuery, ignoreCase = true)
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
            Image(
                painter = painterResource(Res.drawable.back),
                contentDescription = "Go back to course A",
                modifier = Modifier
                    .size(75.dp)
                    .clickable { BackCourses() }
                    .padding(bottom = 16.dp)
            )
            Image(
                painter = painterResource(Res.drawable.profilephoto),
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .clip(CircleShape)
                    .size(65.dp),
                contentScale = ContentScale.Crop
            )
            Text("COURSEB", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
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
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredCourses) { course ->
                CourseCard(
                    title = course.name,
                    resource = course.image
                )
            }
        }
    }
}


@Composable
fun CourseCard(
    title: String,
    resource: DrawableResource?,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable {},
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Space items to edges
        ) {
            // Course details (image + title)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Course image
                Image(
                    painter = painterResource(resource!!),
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

            // "Add Course" button
            Image(
                painter = painterResource(Res.drawable.addcourse),
                contentDescription = "Add Course",
                modifier = Modifier
                    .size(40.dp) // Adjust size
                    .clickable {} // Add click functionality
            )
        }
    }
}
