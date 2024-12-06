
package org.schoolmanager.project
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.datetime.LocalDate
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.schoolmanager.project.data.model.Calendar
import org.schoolmanager.project.data.model.Contact
import org.schoolmanager.project.data.model.NewsHomePage

class SharedViewModel {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)


    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> get() = _contacts

    private val _news= MutableStateFlow<List<NewsHomePage>>(emptyList())
    val news: StateFlow<List<NewsHomePage>> get()= _news

    private val _calendar= MutableStateFlow<List<Calendar>>(emptyList())
    val calendar: StateFlow<List<Calendar>> get()= _calendar


    fun fetchContacts() {
        coroutineScope.launch {
            val fetchedContacts = ApiService.fetchContacts()
            _contacts.value = fetchedContacts
        }
    }

    fun fetchNews() {
        coroutineScope.launch {
            val fetchedNews= ApiService.fetchNews()
            _news.value = fetchedNews
        }
    }

    fun fetchCalendar() {
        coroutineScope.launch {
            val fetchedCalendar = ApiService.fetchCalendar()
            _calendar.value = fetchedCalendar
        }
    }


    fun onClear() {
        coroutineScope.cancel()
    }
}

object ApiService {

    private val client = HttpClient(CIO)

    suspend fun fetchContacts(): List<Contact> { // 172.17.38.18   port 5000
        return try {
            val response: HttpResponse = client.get("http://172.17.38.18:5000/students")
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
            val response: HttpResponse= client.get("http://172.17.38.18:5000/events")
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

    suspend fun fetchCalendar(): List<Calendar> {
        return try {
            val response: HttpResponse= client.get("http://172.17.38.18:5000/calendar")
            if (response.status == HttpStatusCode.OK) {
                val jsonResponse = response.bodyAsText()
                val fetchedData = Json.decodeFromString<List<Calendar>>(jsonResponse)
                fetchedData.map { it.copy(date = LocalDate.parse(it.date).toString()) }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
