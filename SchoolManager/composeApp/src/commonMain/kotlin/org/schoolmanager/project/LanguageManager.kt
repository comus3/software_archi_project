package org.schoolmanager.project

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

expect class LanguageManager() {
    var userLanguagePreference: String?


    fun getCurrentLanguage(): String
    fun setLanguage(languageCode: String)
    fun getSystemLanguage(): String
    fun resetToSystemLanguage()
    fun isLanguageManuallySet(): Boolean
}
