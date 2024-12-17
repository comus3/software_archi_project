// viewmodel/GradesViewModel.kt
package org.schoolmanager.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.launch
import org.schoolmanager.project.ApiService
//import org.schoolmanager.project.data.model.Grade
import org.schoolmanager.project.data.model.SubGrade
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.schoolmanager.project.data.model.NewsHomePage
import org.schoolmanager.project.data.model.StudentCourse
import org.schoolmanager.project.data.model.StudentGrade

class GradesViewModel : ViewModel() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    val _studentsCourses = MutableStateFlow<List<StudentCourse>>(emptyList())
    private val _studentId = MutableStateFlow<String>("")
    val studentId: StateFlow<String> get() = _studentId

    fun setStudentId(id: String) {
        _studentId.value = id
        println("Student ID set in GradesViewModel: $id")
    }


    fun fetchGrades(id_student: String) {
        coroutineScope.launch {
            val fetchedGrades = ApiService.fetchStudentGrades(id_student)
            println("student id in viewmodel: $id_student")
            _studentsCourses.value = fetchedGrades.grades
        }
    }
}

