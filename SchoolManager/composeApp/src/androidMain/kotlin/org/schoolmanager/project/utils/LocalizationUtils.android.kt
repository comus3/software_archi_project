//package org.schoolmanager.project.utils
//
//import androidx.compose.runtime.Composable
//
//
////@Composable
////actual fun loadStringsForLanguage(language: String): Map<String, String> {
////    TODO("Not yet implemented")
////}
//
//
//
//
//import android.content.res.Configuration
//import android.content.res.Resources
//import androidx.compose.ui.platform.LocalContext
//import org.schoolmanager.project.R
//import schoolmanager.composeapp.generated.resources.Res
//import schoolmanager.composeapp.generated.resources.Settings
//import java.util.Locale
//
//@Composable
//actual fun loadStringsForLanguage(language: String): Map<String, String> {
//    val context = LocalContext.current
//    val configuration = Configuration(context.resources.configuration)
//    configuration.setLocale(Locale(language))
//    val localizedContext = context.createConfigurationContext(configuration)
//    val resources: Resources = localizedContext.resources
//
//    // Load the strings from the resources
//    val stringsMap = mutableMapOf<String, String>()
//    val stringIds = listOf(
//        //Res.string.Settings // Add other string IDs here
//        Res.string.Settings
//    )
//    stringIds.forEach { id ->
//        stringsMap[context.resources.getResourceEntryName(id)] = resources.getString(id)
//    }
//
//    return stringsMap
//}



package org.schoolmanager.project.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.Locale
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.Settings

@Composable
actual fun loadStringsForLanguage(language: String): Map<String, String> {
    val context = LocalContext.current
    val configuration = Configuration(context.resources.configuration)
    configuration.setLocale(Locale(language))
    val localizedContext = context.createConfigurationContext(configuration)
    val resources: Resources = localizedContext.resources

    // Load the strings from the resources
    val stringsMap = mutableMapOf<String, String>()
    val stringIds = listOf(
        Res.string.Settings // Add other string IDs here
    )

    stringIds.forEach { id ->
        stringsMap[context.resources.getResourceEntryName(id)] = resources.getString(id)
    }

    return stringsMap
}

