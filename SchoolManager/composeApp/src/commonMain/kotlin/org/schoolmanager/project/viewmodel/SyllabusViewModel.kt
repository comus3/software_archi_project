package org.schoolmanager.project.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.schoolmanager.project.ApiService
import org.schoolmanager.project.data.model.Orientation
import org.schoolmanager.project.data.model.Syllabus


class SyllabusViewModel: ViewModel(){
    private val _orientations = MutableStateFlow<List<Orientation>>(emptyList())
    val orientations: StateFlow<List<Orientation>> get() = _orientations

    private val _syllabus = MutableStateFlow<List<Syllabus>>(emptyList())
    val syllabus: StateFlow<List<Syllabus>> get() = _syllabus

    init {
        fetchOrientations()
        fetchSyllabus()
    }

    // Récupérer les orientations depuis l'API
    fun fetchOrientations() {
        viewModelScope.launch {
            val fetchedOrientations = ApiService.fetchOrientations()
            _orientations.value = fetchedOrientations
        }
    }

    // Récupérer les syllabus depuis l'API
    fun fetchSyllabus() {
        viewModelScope.launch {
            val fetchedSyllabus = ApiService.fetchSyllabus()
            _syllabus.value = fetchedSyllabus
        }
    }



    private val _cartItems = MutableStateFlow<List<Syllabus>>(emptyList())
    val cartItems: StateFlow<List<Syllabus>> get() = _cartItems

    init {
        fetchCart()
    }

    fun fetchCart() {
        viewModelScope.launch {
            _cartItems.value = ApiService.fetchCart()
        }
    }

    fun addToCart(item: Syllabus) {
        viewModelScope.launch {
            ApiService.addToCart(item)
            fetchCart()
        }
    }

    fun removeFromCart(syllabusId: Int) {
        viewModelScope.launch {
            ApiService.removeFromCart(syllabusId)
            fetchCart()
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            ApiService.clearCart()
            fetchCart()
        }
    }

}