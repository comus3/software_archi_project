//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import io.ktor.client.*
//import io.ktor.client.engine.cio.*
//import io.ktor.client.request.*
//import io.ktor.client.statement.*
//import io.ktor.http.*
//private val _coursesResult = MutableStateFlow("Appuyez sur le bouton pour charger les cours.")
//val coursesResult: StateFlow<String> = _coursesResult // Fonction pour récupérer les cours à partir du serveur
//fun fetchCourses() {
//    viewModelScope.launch {
//        try {
//            val client = HttpClient(CIO)
//            val response: HttpResponse = client.get("http://pat.infolab.ecam.be:61818/courses")
//
//            if (response.status == HttpStatusCode.OK) {
//                _coursesResult.value = response.bodyAsText()
//            } else {
//                _coursesResult.value = "Erreur : ${response.status}"
//            }
//            client.close()
//        } catch (e: Exception) {
//            _coursesResult.value = "Erreur lors de la requête : ${e.message}"
//        }
//    }
//}