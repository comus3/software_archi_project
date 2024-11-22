package org.schoolmanager.project.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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

fun TermsScreen(BackSettings:()-> Unit){
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
                contentDescription = "PreviousPage",
                modifier = Modifier
                    .size(75.dp)
                    .clickable { BackSettings() }
                    .padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.width(85.dp))

            Text(
                "Terms and Conditions",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f) // Allow the text to take up proportional space
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.weight(1f)) // Equal spacing after the Text
        }
        Text(
            "Here are the terms"
        )
    }
}