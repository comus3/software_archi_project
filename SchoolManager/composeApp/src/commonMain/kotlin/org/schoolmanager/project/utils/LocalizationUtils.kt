package org.schoolmanager.project.utils
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf



//// Define LocalStrings as a CompositionLocal
//val LocalStrings = compositionLocalOf {
//    // Provide a default value or a map of strings
//    mapOf("key" to "defaultValue")
//}

//// Define loadStringsForLanguage function
//fun loadStringsForLanguage(language: String): Map<String, String> {
//    // Implement the logic to load strings for the given language
//    // This could involve reading from a resource file, database, etc.
//    return when (language) {
//        "en" -> mapOf("key" to "valueInEnglish")
//        "fr" -> mapOf("key" to "valueInFrench")
//        else -> mapOf("key" to "defaultValue")
//    }
//}




//// Define loadStringsForLanguage function
//@Composable
//fun loadStringsForLanguage(language: String): Map<String, String> {
//    val context = LocalContext.current
//    val configuration = Configuration(context.resources.configuration)
//    configuration.setLocale(Locale(language))
//    val localizedContext = context.createConfigurationContext(configuration)
//    val resources: Resources = localizedContext.resources
//
//    // Load the strings from the resources
//    val stringsMap = mutableMapOf<String, String>()
//    val stringIds = listOf(
//        R.string.Settings // Add other string IDs here
//    )
//
//    stringIds.forEach { id ->
//        stringsMap[context.resources.getResourceEntryName(id)] = resources.getString(id)
//    }
//
//    return stringsMap
//}




//// Define LocalStrings as a CompositionLocal
//val LocalStrings = compositionLocalOf {
//    // Provide a default value or a map of strings
//    mapOf("key" to "defaultValue")
//}
//
//// Define loadStringsForLanguage function as expect
//@Composable
//expect fun loadStringsForLanguage(language: String): Map<String, String>

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

// Define LocalStrings as a CompositionLocal
val LocalStrings = compositionLocalOf {
    // Provide a default value or a map of strings
    mapOf("key" to "defaultValue")
}

// Define loadStringsForLanguage function as expect
@Composable
expect fun loadStringsForLanguage(language: String): Map<String, String>

// Function to load JSON localization files
fun loadLocalizationFile(language: String): Map<String, String> {
    val classLoader = LocalStrings::class.java.classLoader
    val jsonString = classLoader?.getResourceAsStream("localization/$language.json")?.bufferedReader().use { it?.readText() }
    val jsonObject = Json.parseToJsonElement(jsonString ?: "{}").jsonObject
    return jsonObject.entries.associate { it.key to (it.value.jsonPrimitive.content) }
}