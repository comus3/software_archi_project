package org.schoolmanager.project.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.back

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun LanguageCard(text: String) {
    var showDialog by remember { mutableStateOf(false) } // Ensure mutableStateOf is properly imported

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { showDialog = true } // Show dialog on click
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = text, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }

    Spacer(modifier = Modifier.height(8.dp)) // Space between the cards

    // Pop-up Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirmation") },
            text = { Text("Switching to $text") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    // Add "Yes" action here
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                    // Add "No" action here
                }) {
                    Text("No")
                }
            }
        )
    }
}

@Composable
fun LanguageScreen(BackSettings:()-> Unit){
    Column( // Use Column to stack the elements vertically
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // First Row: Back button and "Settings" text
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.back),
                contentDescription = "Settings",
                modifier = Modifier
                    .size(75.dp)
                    .clickable { BackSettings() }
                    .padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.width(85.dp))

            Text(
                "Language",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f) // Allow the text to take up proportional space
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.weight(1f)) // Equal spacing after the Text
            }

        LanguageCard("English")
        LanguageCard("Français")
        LanguageCard("Neederlands")
        LanguageCard("Español")
        }
}