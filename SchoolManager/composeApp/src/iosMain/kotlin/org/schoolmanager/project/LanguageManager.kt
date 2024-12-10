package org.schoolmanager.project
//
//import platform.Foundation.NSLocale
//
//actual class LanguageManager {
//    actual private var userLanguagePreference: String? = null
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
//        return NSLocale.currentLocale.languageCode ?: "en"
//    }
//
//    actual fun resetToSystemLanguage() {
//        userLanguagePreference = null
//    }
//
//    actual fun isLanguageManuallySet(): Boolean = userLanguagePreference != null
//}