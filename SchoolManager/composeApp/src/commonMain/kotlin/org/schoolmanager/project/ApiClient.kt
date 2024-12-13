
package org.schoolmanager.project
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.LocalDate
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.schoolmanager.project.data.model.API_Course
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

    private val _orientations= MutableStateFlow<List<Orientation>>(emptyList())
    val orientation: StateFlow<List<Orientation>> get()= _orientations

    private val _syllabus= MutableStateFlow<List<Syllabus>>(emptyList())
    val syllabus: StateFlow<List<Syllabus>> get()= _syllabus

    private val _cart= MutableStateFlow<List<Syllabus>>(emptyList())
    val cart: StateFlow<List<Syllabus>> get()= _cart

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

    fun fetchOrientations() {
        coroutineScope.launch {
            val fetchedOrientations = ApiService.fetchOrientations()
            _orientations.value = fetchedOrientations
        }
    }

    fun fetchSyllabus() {
        coroutineScope.launch {
            val fetchedSyllabus = ApiService.fetchSyllabus()
            _syllabus.value = fetchedSyllabus
        }
    }

    fun fetchCart() {
        coroutineScope.launch {
            val fetchedCart = ApiService.fetchCart()
            _cart.value = fetchedCart
        }
    }


    //addToCart
    //removeFromCart
    //clearCart
//    fun addToCart() {
//        coroutineScope.launch {
//            val addedToCart = ApiService.addToCart()
//            _cart.value = addedToCart
//        }
//    }

    fun addToCart(syllabus: Syllabus) {
        coroutineScope.launch {
            try {
                ApiService.addToCart(syllabus)
                println("Article ajouté au panier : $syllabus")
                fetchCart() // Rafraîchit le panier après l'ajout
            } catch (e: Exception) {
                println("Erreur lors de l'ajout au panier : ${e.message}")
            }
        }
    }

    fun removeFromCart(syllabusId: Int) {
        coroutineScope.launch {
            try {
                ApiService.removeFromCart(syllabusId)
                println("Article supprimé du panier : $syllabusId")
                fetchCart() // Rafraîchit le panier après la suppression
            } catch (e: Exception) {
                println("Erreur lors de la suppression du panier : ${e.message}")
            }
        }
    }

    fun clearCart() {
        coroutineScope.launch {
            try {
                ApiService.clearCart()
                println("Panier vidé")
                fetchCart() // Rafraîchit le panier après le nettoyage
            } catch (e: Exception) {
                println("Erreur lors du nettoyage du panier : ${e.message}")
            }
        }
    }


    fun onClear() {
        coroutineScope.cancel()
    }
}

object ApiService {

    private val client = HttpClient(CIO){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // Ignore les champs inconnus
                isLenient = true // Accepte des formats JSON plus souples
                prettyPrint = false // Désactive l'impression formatée
            })
        }
    }

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
            val response: HttpResponse = client.get("http://pat.infolab.ecam.be:61818/orientations")
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
            val response: HttpResponse = client.get("http://pat.infolab.ecam.be:61818/syllabus")
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
            val response: HttpResponse = client.get("http://pat.infolab.ecam.be:61818/cart")
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

//    suspend fun addToCart(syllabus: Syllabus): Boolean {
//        return try {
//            val response: HttpResponse = client.post("http://pat.infolab.ecam.be:61818/cart") {
//                contentType(ContentType.Application.Json)
//                setBody(syllabus)
//            }
//            if (response.status == HttpStatusCode.OK) {
//                println("Requête POST réussie : $syllabus")
//                true
//            } else {
//                println("Erreur lors de la requête POST : ${response.status}")
//                false
//            }
//        } catch (e: Exception) {
//            println("Exception lors de l'ajout au panier : ${e.message}")
//            false
//        }
//    }

    suspend fun addToCart(syllabus: Syllabus) {
        try {
            val response: HttpResponse = client.post("http://pat.infolab.ecam.be:61818/cart") {
                contentType(ContentType.Application.Json)
                setBody(syllabus) // Ici, `syllabus` sera automatiquement sérialisé en JSON
            }
            println("Requête POST réussie pour ajouter au panier : ${response.status}")
        } catch (e: Exception) {
            println("Erreur lors de la requête POST : ${e.message}")
        }
    }

    suspend fun removeFromCart(syllabusId: Int): Boolean {
        return try {
            val response: HttpResponse = client.delete("http://pat.infolab.ecam.be:61818/cart/$syllabusId")
            if (response.status == HttpStatusCode.OK) {
                println("Requête DELETE réussie pour l'article $syllabusId")
                true
            } else {
                println("Erreur lors de la requête DELETE : ${response.status}")
                false
            }
        } catch (e: Exception) {
            println("Exception lors de la suppression du panier : ${e.message}")
            false
        }
    }

    suspend fun clearCart(): Boolean {
        return try {
            val response: HttpResponse = client.delete("http://pat.infolab.ecam.be:61818/cart")
            if (response.status == HttpStatusCode.OK) {
                println("Panier vidé avec succès")
                true
            } else {
                println("Erreur lors de la requête DELETE : ${response.status}")
                false
            }
        } catch (e: Exception) {
            println("Exception lors du nettoyage du panier : ${e.message}")
            false
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

    suspend fun fetchCourses(): List<API_Course> {
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
