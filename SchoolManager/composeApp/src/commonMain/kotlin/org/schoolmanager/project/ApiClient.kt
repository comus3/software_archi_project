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
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.schoolmanager.project.data.model.Contact


object ApiService {

    // Fonction pour récupérer les contacts depuis l'API
    suspend fun fetchContacts(): List<Contact> {
        // Créer un client HTTP
        val client = HttpClient(CIO)

        return try {
            // Effectuer la requête HTTP GET
            val response: HttpResponse = client.get("http://pat.infolab.ecam.be:61818/students")

            // Vérifier si la réponse est OK
            if (response.status == HttpStatusCode.OK) {
                val jsonResponse = response.bodyAsText()
                // Utiliser kotlinx.serialization pour convertir la réponse JSON en liste de contacts
                Json.decodeFromString<List<Contact>>(jsonResponse)
            } else {
                emptyList() // Retourne une liste vide en cas d'erreur
            }
        } catch (e: Exception) {
            // En cas d'erreur, retourne une liste vide et logge l'erreur
            emptyList()
        } finally {
            // Fermer le client HTTP après la requête
            client.close()
        }
    }
}