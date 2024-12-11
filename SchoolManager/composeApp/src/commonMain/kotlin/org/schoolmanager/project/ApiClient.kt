
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
import org.schoolmanager.project.data.model.Course
//import org.schoolmanager.project.data.model.Grade
import org.schoolmanager.project.data.model.NewsHomePage
import org.schoolmanager.project.data.model.Orientation
import org.schoolmanager.project.data.model.Syllabus
import org.schoolmanager.project.data.model.StudentGrade

//import org.schoolmanager.project.data.model.StudentGradesResponse

class SharedViewModel {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)


    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> get() = _contacts

    private val _news= MutableStateFlow<List<NewsHomePage>>(emptyList())
    val news: StateFlow<List<NewsHomePage>> get()= _news

    private val _calendar= MutableStateFlow<List<Calendar>>(emptyList())
    val calendar: StateFlow<List<Calendar>> get()= _calendar

//    private val _grades = MutableStateFlow<List<Grade>>(emptyList())
//    val grade: StateFlow<List<Grade>> get() = _grades


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

//    fun fetchGrades() {
//        coroutineScope.launch {
//            val fetchedGrades = ApiService.fetchGrades()
//            _grades.value = fetchedGrades
//        }
//    }


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
            val response: HttpResponse= client.get("http://pat.infolab.ecam.be:61818/news")
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
//http://172.17.38.18:5000/calendar
    suspend fun fetchCalendar(): List<Calendar> {
        return try {
            val response: HttpResponse= client.get("http://pat.infolab.ecam.be:61818/calendar")
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




    // Méthode pour récupérer les orientations
    suspend fun fetchOrientations(): List<Orientation> {
        return try {
            val response: HttpResponse = client.get("http://localhost:3323/orientations")
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

    // Méthode pour récupérer les syllabus
    suspend fun fetchSyllabus(): List<Syllabus> {
        return try {
            val response: HttpResponse = client.get("http://localhost:3323/syllabus")
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





    suspend fun fetchCart(): List<Syllabus> {
        return try {
            val response: HttpResponse = client.get("http://localhost:3323/cart")
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

    suspend fun addToCart(syllabus: Syllabus) {
        try {
            client.post("http://localhost:3323/cart") {
                contentType(ContentType.Application.Json)
                setBody(syllabus)
            }
        } catch (e: Exception) {
            // Gérer les erreurs
        }
    }

    suspend fun removeFromCart(syllabusId: Int) {
        try {
            client.delete("http://localhost:3323/cart/$syllabusId")
        } catch (e: Exception) {
            // Gérer les erreurs
        }
    }

    suspend fun clearCart() {
        try {
            client.delete("http://localhost:3323/cart")
        } catch (e: Exception) {
            // Gérer les erreurs
        }
    }

    suspend fun fetchStudentGrades(): StudentGrade {
        return try {
            val response: HttpResponse = client.get("http://pat.infolab.ecam.be:61818/grades/20231")
            val jsonResponse = response.bodyAsText()
            Json.decodeFromString(jsonResponse)
        } catch (e: Exception) {
            StudentGrade(grades = emptyList(), student_id = "")
        }
    }

    suspend fun fetchCourses(): List<Course> {
        return try {
            val response: HttpResponse = client.get("http://pat.infolab.ecam.be:61818/courses")
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
