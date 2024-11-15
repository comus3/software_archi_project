// ui.grades/BottomNavigationBar.kt
package org.schoolmanager.project.ui.grades

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Text
import androidx.compose.material.TextButton

@Composable
fun BottomNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TextButton(onClick = { /* TODO: navigate to Home */ }) {
            Text("Home", color = Color.White)
        }
        TextButton(onClick = { /* TODO: navigate to Calendar */ }) {
            Text("Calendar", color = Color.White)
        }
        TextButton(onClick = { /* TODO: navigate to Courses */ }) {
            Text("Courses", color = Color.White)
        }
        TextButton(onClick = { /* TODO: navigate to Contact */ }) {
            Text("Contact", color = Color.White)
        }
        TextButton(onClick = { /* TODO: navigate to Profile */ }) {
            Text("Profile", color = Color(0xFF9C27B0)) // Highlighted
        }
    }
}