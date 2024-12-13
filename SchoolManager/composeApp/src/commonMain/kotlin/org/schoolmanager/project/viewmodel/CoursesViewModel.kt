package org.schoolmanager.project.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

import org.schoolmanager.project.ApiService
import org.schoolmanager.project.data.model.Course
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.administration_reseau
import schoolmanager.composeapp.generated.resources.alternatif_monophase
import schoolmanager.composeapp.generated.resources.electronic_circuit
import schoolmanager.composeapp.generated.resources.motor

import kotlinx.coroutines.flow.stateIn
import org.schoolmanager.project.data.model.API_Course
import org.schoolmanager.project.data.model.Contact


class CoursesViewModel : ViewModel() {
    private val courses = listOf(
        Course(1, "Electrical", Res.drawable.alternatif_monophase, "LURKIN Quentin", "lrk@ecam.be"),
        Course(2, "Motors", Res.drawable.motor, "LOUIS Jean-Guillaume", "j3l@ecam.be"),
        Course(3, "Math", Res.drawable.electronic_circuit, "HILLEWAERE Ruben", "hlr@ecam.be"),
        Course(4, "Network", Res.drawable.administration_reseau, "DELHAY Quentin", "dlh@ecam.be"),
    )

    //FCT TO GET THE COURSE BY ID
    fun getCourseById(id: Int): Course? {
        return courses.find { it.id == id }
    }

    fun getAllCourses(): List<Course> {
        return courses
    }

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    val api_courses = MutableStateFlow<List<API_Course>>(emptyList())
    var searchQuery = MutableStateFlow("")

    fun fetchCourses() {
        coroutineScope.launch {
            val fetchedCourses = ApiService.fetchCourses()
            api_courses.value = fetchedCourses
        }
    }

    val filteredCourses: StateFlow<List<API_Course>>
        get() = api_courses.map { coursesList ->
            coursesList.filter { it.name.contains(searchQuery.value, ignoreCase = true) }
        }.stateIn(
            coroutineScope,
            SharingStarted.Lazily,
            emptyList()
        )


    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }
}