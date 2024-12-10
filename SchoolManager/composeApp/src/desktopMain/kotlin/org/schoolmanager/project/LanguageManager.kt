package org.schoolmanager.project


actual class LanguageManager {
    actual var userLanguagePreference: String? = null

    actual fun getCurrentLanguage(): String {
        userLanguagePreference?.let { return it }
        return getSystemLanguage()
    }

    actual fun setLanguage(languageCode: String) {
        userLanguagePreference = languageCode
    }

    actual fun getSystemLanguage(): String {
        return java.util.Locale.getDefault().language
    }

    actual fun resetToSystemLanguage() {
        userLanguagePreference = null
    }

    actual fun isLanguageManuallySet(): Boolean = userLanguagePreference != null
}