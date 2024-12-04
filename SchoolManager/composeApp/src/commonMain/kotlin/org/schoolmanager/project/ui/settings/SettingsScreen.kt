package org.schoolmanager.project.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.arrowRight
import schoolmanager.composeapp.generated.resources.back
import schoolmanager.composeapp.generated.resources.darkMode
import schoolmanager.composeapp.generated.resources.language
import schoolmanager.composeapp.generated.resources.phoneIcon
import schoolmanager.composeapp.generated.resources.termsAndConditions

@Composable
fun SettingsButton(
    text: String,
    imageResource: DrawableResource, // Use Int if it's a resource ID
    imageContentDescription: String?,
    onClick: () -> Unit
) {
    val iconColor = MaterialTheme.colors.onSurface
    val arrowColor = MaterialTheme.colors.onSurface
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(imageResource), // Use the parameter directly
                    contentDescription = imageContentDescription,
                    modifier = Modifier.size(40.dp),
                    colorFilter = ColorFilter.tint(iconColor)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Arrow Icon aligned to the end
            Image(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Arrow Icon",
                modifier = Modifier
                    .size(30.dp)
                    .clickable(onClick = onClick),
                colorFilter = ColorFilter.tint(arrowColor)


            )

        }
    }
}

@Composable
fun BannerButton(
    text: String,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun SettingsScreen(
    BackProfile: () -> Unit = {},
    GoToAbout: () -> Unit = {},
    GoToLanguage: () -> Unit = {},
    GoToTerms: () -> Unit = {},
    onDarkModeToggle: (Boolean) -> Unit
){
    val iconColor = MaterialTheme.colors.onSurface
    val arrowColor = MaterialTheme.colors.onSurface
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
                    .clickable { BackProfile() }
                    .padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.width(85.dp))

            Text(
                "Settings",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f) // Allow the text to take up proportional space
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.weight(1f)) // Equal spacing after the Text
        }

        Spacer(modifier = Modifier.height(16.dp)) // Space between Rows

        // General Button

        BannerButton(
            text = "General",
            backgroundColor = Color(0xFF455A64)
        )


        // Language Button
        SettingsButton(
            text = "Language",
            imageResource = Res.drawable.language,
            imageContentDescription = "Language Icon",
            onClick = { GoToLanguage() }
        )

        // Dark Mode
        // State to manage the toggle state
        var isDarkModeEnabled by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .fillMaxWidth() // Adjust width of the box
                .padding(16.dp) // Padding inside the box
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), // Make the Row span the entire width
                verticalAlignment = Alignment.CenterVertically, // Center-align items vertically
                horizontalArrangement = Arrangement.SpaceBetween // Space out elements to left and right
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically // Inner row for icon and text alignment
                ){
                    Image(
                        painter = painterResource(Res.drawable.darkMode),
                        contentDescription = "Dark Mode Icon",
                        modifier = Modifier.size(40.dp),
                        colorFilter = ColorFilter.tint(iconColor)// Set the image sizecolorFilter = ColorFilter.tint(iconColor)
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Optional: Adds space between the image and text
                    Text(
                        "Dark Mode",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                // Add the Switch component
                Switch(
                    checked = isDarkModeEnabled,
                    onCheckedChange = { isChecked ->
                        isDarkModeEnabled = isChecked
                        onDarkModeToggle(isChecked) // Appelle la fonction pour changer le mode
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

            }
        }
        // Infos section
        BannerButton(
            text = "Infos",
            backgroundColor = Color(0xFF455A64)
        )
        // About button

        SettingsButton(
            text = "About",
            imageResource = Res.drawable.phoneIcon,
            imageContentDescription = "About Icon",
            onClick = { GoToAbout() }
        )

        // Terms and conditions button

        SettingsButton(
            text = "Terms and Conditions",
            imageResource = Res.drawable.termsAndConditions,
            imageContentDescription = "Terms Icon",
            onClick = { GoToTerms() }
        )

    }
}