package org.schoolmanager.project

actual class LanguageManager {
    actual var userLanguagePreference: String?
        get() = TODO("Not yet implemented")
        set(value) {}

    actual fun getCurrentLanguage(): String {
        TODO("Not yet implemented")
    }

    actual fun setLanguage(languageCode: String) {
    }

    actual fun getSystemLanguage(): String {
        TODO("Not yet implemented")
    }

    actual fun resetToSystemLanguage() {
    }

    actual fun isLanguageManuallySet(): Boolean {
        TODO("Not yet implemented")
    }

}