import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import org.schoolmanager.project.ui.settings.SettingsScreen

val DarkColorPalette = darkColors(
    primary = Color(0xFF6200EA),
    onPrimary = Color.White,
    primaryVariant = Color(0xFF3700B3)
)

val LightColorPalette = lightColors(
    primary = Color(0xFFBB86FC),
    onPrimary = Color.Black,
    primaryVariant = Color(0xFF6200EA)
)
val Typography = Typography()
@Composable
fun MyTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography, // Utilisation de l'objet Typography par défaut
        shapes = MaterialTheme.shapes, // Par défaut, ou personnalise si nécessaire
        content = content
    )
}
@Composable
fun MyApp() {
    var isDarkModeEnabled by remember { mutableStateOf(false) }

    MyTheme(darkTheme = isDarkModeEnabled) {
        SettingsScreen(
            onDarkModeToggle = { isDarkModeEnabled = it }
        )
    }
}
