package org.schoolmanager.project.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.schoolmanager.project.viewmodel.ProfileViewModel
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.discord
import schoolmanager.composeapp.generated.resources.iconcontact
import schoolmanager.composeapp.generated.resources.mail
import schoolmanager.composeapp.generated.resources.profilePicture





@Composable
fun ProfileScreen(BackHomePage: ()-> Unit, GoToSettings: ()-> Unit) {
    val viewModel = ProfileViewModel()

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter= painterResource(Res.drawable.iconcontact),
                contentDescription= "Settings",
                modifier= Modifier
                    .size(40.dp)
                    .clickable {BackHomePage()}
                    .padding(bottom = 16.dp)
            )

            // "John Doe" Text
            Text(
                "John Doe",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Image(
                painter= painterResource(Res.drawable.iconcontact),
                contentDescription= "Settings",
                modifier= Modifier
                    .size(40.dp)
                    .clickable {GoToSettings()}
                    .padding(bottom = 16.dp)
            )
        }

        Image(
            painter = painterResource(Res.drawable.profilePicture), // Correct image resource reference
            contentDescription = "Profile Picture",
            modifier = Modifier
                .padding(top = 20.dp) // Add padding to move the image down
                .align(Alignment.CenterHorizontally) // Center the image horizontally
        )


        Text(
            "Contact Infos",
            Modifier.padding(top = 35.dp),
            fontSize = 20.sp, // Set the font size to 20sp
            fontWeight = FontWeight.Bold // Make the text bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth() // Ensure the row takes up the full width
                .padding(top = 16.dp), // Add padding to the top of the row
            horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between buttons
            verticalAlignment = Alignment.CenterVertically // Align the buttons vertically in the center
        ) {
            // First Button ("+")
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .width(40.dp) // Set specific width for the button
                    .height(40.dp), // Set specific height for the button
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White), // Button background color
                shape = MaterialTheme.shapes.medium // Rounded corners
            ) {
                Text("+")
            }

            // Second Button
            MailButton()

            // Third Button
            DiscordButton()
        }


        // List of Courses with Styled Header
        Text(
            "Courses",
            Modifier.padding(top = 35.dp),
            fontSize = 20.sp, // Set the font size to 20sp
            fontWeight = FontWeight.Bold // Make the text bold
        )
        LazyColumn(
            modifier = Modifier
                .align(Alignment.Start) // Align the LazyColumn to the start (left)
                .padding(start = 40.dp) // Optional: Add padddding to the start (left side) of the LazyColumn
        ) {
            items(viewModel.courses) { course ->
                Text(course.name, Modifier.padding(8.dp))
            }

        }
        Text(
            "Grades",
            Modifier.padding(top = 35.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 16.dp) // Space between the list and the buttons
                .fillMaxWidth(), // Ensure it fills the screen width
            horizontalArrangement = Arrangement.spacedBy(18.dp) // Space between buttons
        ) {
            items(3) { index ->
                Button(
                    onClick = { /* Handle button click for index */ },
                    modifier = Modifier
                        .width(100.dp) // Set specific width for each button
                        .padding(12.dp), // Padding around each button
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "BA ${index + 1}",
                        color = Color.Black, // White text
                        fontWeight = FontWeight.Bold
                        // If the guy is in master, check for it
                    )
                }
            }
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

