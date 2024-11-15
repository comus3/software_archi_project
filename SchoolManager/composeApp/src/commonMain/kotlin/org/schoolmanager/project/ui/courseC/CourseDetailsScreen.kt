package org.schoolmanager.project.ui.courseC

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.back

@Composable
fun CourseDetailsScreen(BackCourses: ()-> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(), // Fill the available size
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically)
        {
            Image(
                painter = painterResource(Res.drawable.back),
                contentDescription = "Settings",
                modifier = Modifier
                    .size(75.dp)
                    .clickable {BackCourses() }
                    .padding(bottom = 16.dp)
            )
            Text("COURSEC", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}
