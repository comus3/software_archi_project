package org.schoolmanager.project.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.schoolmanager.project.viewmodel.ProfileViewModel
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.discord
import schoolmanager.composeapp.generated.resources.mail
import schoolmanager.composeapp.generated.resources.profilePicture
import schoolmanager.composeapp.generated.resources.back
import schoolmanager.composeapp.generated.resources.settings





@Composable
fun ProfileScreen(BackHomePage: () -> Unit, GoToSettings: () -> Unit, GoToGrades: () -> Unit) {
    val viewModel = ProfileViewModel()
    val iconColor = MaterialTheme.colors.onSurface
    // Use LazyColumn for all scrollable content
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(), // Fill the available size
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(Res.drawable.back),
                    contentDescription = "Settings",
                    modifier = Modifier
                        .size(75.dp)
                        .clickable { BackHomePage() }
                        .padding(bottom = 16.dp),
                    colorFilter = ColorFilter.tint(iconColor)
                )

                Text("John Doe", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Image(
                    painter = painterResource(Res.drawable.settings),
                    contentDescription = "Settings",
                    modifier = Modifier
                        .size(75.dp)
                        .clickable { GoToSettings() }
                        .padding(bottom = 16.dp),
                    colorFilter = ColorFilter.tint(iconColor)
                )
            }
        }

        item {
            Image(
                painter = painterResource(Res.drawable.profilePicture),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .padding(top = 20.dp)
            )
        }

        item {
            Text(
                "Contact Infos",
                Modifier.padding(top = 35.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { /* Handle button click */ },
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("+")
                }

                MailButton()
                DiscordButton()
            }
        }

        item {
            Text("Courses", Modifier.padding(top = 35.dp), fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        // Adding all courses
        items(viewModel.courses) { course ->
            Text(course.name, Modifier.padding(8.dp))
        }

        item {
            Text("Grades", Modifier.padding(top = 35.dp), fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        item {
            LazyRow(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                items(3) { index ->
                    Button(
                        onClick = {GoToGrades()},
                        modifier = Modifier
                            .width(100.dp)
                            .padding(12.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = "BA ${index + 1}",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold

                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(60.dp)) // Adjust the height as needed
        }
    }
}

@Composable
fun MailButton() {
    Button(
        onClick = { /* Handle image button click */ },
        modifier = Modifier
            .padding(0.dp) // Remove padding around the button
            .size(40.dp), // Set the size of the button based on the image size
        contentPadding = PaddingValues(0.dp), // Remove extra padding inside the button
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent), // Make button background transparent
        shape = MaterialTheme.shapes.medium // Rounded corners
    ) {
        Image(
            painter = painterResource(Res.drawable.mail), // Path to your image
            contentDescription = "Email Logo",
            modifier = Modifier.fillMaxSize() // Make the image fill the button area
        )
    }
}

@Composable
fun DiscordButton() {
    Button(
        onClick = { /* Handle image button click */ },
        modifier = Modifier
            .padding(0.dp) // Remove padding around the button
            .size(40.dp), // Set the size of the button based on the image size
        contentPadding = PaddingValues(0.dp), // Remove extra padding inside the button
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent), // Make button background transparent
        shape = MaterialTheme.shapes.medium // Rounded corners
    ) {
        Image(
            painter = painterResource(Res.drawable.discord), // Path to your image
            contentDescription = "Discord Logo",
            modifier = Modifier.fillMaxSize() // Make the image fill the button area
        )
    }
}

