//package org.schoolmanager.project
//
//actual class LanguageManager {
//    actual var userLanguagePreference: String? = null
//
//    actual fun getCurrentLanguage(): String {
//        userLanguagePreference?.let { return it }
//        return getSystemLanguage()
//    }
//
//    actual fun setLanguage(languageCode: String) {
//        userLanguagePreference = languageCode
//    }
//
//    actual fun getSystemLanguage(): String {
//        return java.util.Locale.getDefault().language
//    }
//
//    actual fun resetToSystemLanguage() {
//        userLanguagePreference = null
//    }
//
//    actual fun isLanguageManuallySet(): Boolean = userLanguagePreference != null
//}

package org.schoolmanager.project

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

actual class LanguageManager {
    actual var userLanguagePreference: String? by mutableStateOf(null)

    actual fun getCurrentLanguage(): String {
        return userLanguagePreference ?: getSystemLanguage()
    }

    actual fun setLanguage(languageCode: String) {
        userLanguagePreference = languageCode
    }

    actual fun getSystemLanguage(): String {
        // Implementation to get the system language
        return java.util.Locale.getDefault().language
    }

    actual fun resetToSystemLanguage() {
        userLanguagePreference = null
    }

    actual fun isLanguageManuallySet(): Boolean {
        return userLanguagePreference != null
    }
}