
package org.schoolmanager.project
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.schoolmanager.project.data.model.Contact
import org.schoolmanager.project.data.model.NewsHomePage

class SharedViewModel {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)


    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> get() = _contacts

    private val _news= MutableStateFlow<List<NewsHomePage>>(emptyList())
    val news: StateFlow<List<NewsHomePage>> get()= _news


    fun fetchContacts() {
        coroutineScope.launch {
            val fetchedContacts = ApiService.fetchContacts()
            _contacts.value = fetchedContacts
        }
    }

    fun fetchNews() {
        coroutineScope.launch {
            val fetchedNews = ApiService.fetchNews()
            _news.value = fetchedNews
        }
    }


    fun onClear() {
        coroutineScope.cancel()
    }
}

object ApiService {

    private val client = HttpClient(CIO)

    suspend fun fetchContacts(): List<Contact> {
        return try {
            val response: HttpResponse = client.get("http://pat.infolab.ecam.be:61818/students")
            if (response.status == HttpStatusCode.OK) {
                val jsonResponse = response.bodyAsText()
                Json.decodeFromString(jsonResponse)
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun fetchNews(): List<NewsHomePage> {
        return try {
            val response: HttpResponse= client.get("http://pat.infolab.ecam.be:61818/events")
            if (response.status == HttpStatusCode.OK) {
                val jsonResponse = response.bodyAsText()
                Json.decodeFromString(jsonResponse)
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
