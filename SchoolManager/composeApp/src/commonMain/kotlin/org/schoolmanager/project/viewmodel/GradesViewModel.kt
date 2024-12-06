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
    //val studentsCourses: StateFlow<List<StudentCourse>> get() = _studentsCourses

    fun fetchGrades() {
        coroutineScope.launch {
            val fetchedGrades = ApiService.fetchStudentGrades()
            _studentsCourses.value = fetchedGrades.grades
        }
    }

//    fun getStudentGrades(studentId: String): StudentGrade? {
//        return studentsCourses.value.find { it.student_id == studentId }
//    }
}

