package org.schoolmanager.project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope

private val contact_results = MutableStateFlow("Appuyez sur le bouton pour charger les contacts.")
val contactResult: StateFlow<String> = contact_results
val appScope = CoroutineScope(Dispatchers.Default)
// Fonction pour récupérer les cours
fun fetchCourses() {
    appScope.launch {
        try {
            val client = HttpClient(CIO)
            val response = client.get("http://pat.infolab.ecam.be:61818/students")

            // Vérification de la réponse
            if (response.status == HttpStatusCode.OK) {
                contact_results.value = response.bodyAsText()
            } else {
                contact_results.value = "Erreur : ${response.status}"
            }

            client.close()
        } catch (e: Exception) {
            contact_results.value = "Erreur lors de la requête : ${e.message}"
        }
    }
}