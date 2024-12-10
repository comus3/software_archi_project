package org.schoolmanager.project.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.schoolmanager.project.ApiService
import org.schoolmanager.project.data.model.Course
import org.schoolmanager.project.data.model.NewsHomePage
import org.schoolmanager.project.data.model.Orientation
import org.schoolmanager.project.data.model.Syllabus


class SyllabusViewModel: ViewModel(){

    private val coroutineScope= CoroutineScope(SupervisorJob() + Dispatchers.Default)
    val orientations= MutableStateFlow<List<Orientation>>(emptyList())
    val syllabus= MutableStateFlow<List<Syllabus>>(emptyList())
    val cartItems= MutableStateFlow<List<Syllabus>>(emptyList())


    // Récupérer les orientations depuis l'API
    fun fetchOrientations() {
        coroutineScope.launch {
            val fetchedOrientations = ApiService.fetchOrientations()
            orientations.value = fetchedOrientations
        }
    }

    // Récupérer les syllabus depuis l'API
    fun fetchSyllabus() {
        coroutineScope.launch {
            val fetchedSyllabus = ApiService.fetchSyllabus()
            syllabus.value = fetchedSyllabus
        }
    }

    fun getSyllabusByOrientationId(orientationId: Int): List<Syllabus> {
        return syllabus.value.filter { it.idorientation == orientationId }
    }




    init {
        fetchCart()
    }

    fun fetchCart() {
        coroutineScope.launch {
            cartItems.value = ApiService.fetchCart()
        }
    }

//    fun addToCart(item: Syllabus) {
//        coroutineScope.launch {
//            ApiService.addToCart(item)
//            fetchCart()
//        }
//    }
fun addToCart(syllabus: Syllabus) {
    coroutineScope.launch {
        try {
            println("Ajout de l'article au panier : $syllabus")
            ApiService.addToCart(syllabus)
        } catch (e: Exception) {
            println("Erreur dans ViewModel addToCart : ${e.message}")
        }
    }
}

    fun removeFromCart(syllabusId: Int) {
        coroutineScope.launch {
            ApiService.removeFromCart(syllabusId)
            fetchCart()
        }
    }

    fun clearCart() {
        coroutineScope.launch {
            ApiService.clearCart()
            fetchCart()
        }
    }

}