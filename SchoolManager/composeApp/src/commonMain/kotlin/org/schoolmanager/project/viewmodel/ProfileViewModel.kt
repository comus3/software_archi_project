package org.schoolmanager.project.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import org.schoolmanager.project.data.model.Course

class ProfileViewModel : ViewModel(){
    val courses = mutableStateListOf(
        Course("Mathematics 101"),
        Course("Physics 201"),
        Course("Computer Science 305"),
        Course("History 210"),
        Course("Biology 102"),
        Course("Biology 102"),
        Course("Biology 102"),
        Course("Biology 102"),
        Course("Biology 102"),
        Course("Biology 102"),
        Course("Biology 102"),
        Course("Biology 102"),
        Course("Biology 102"),
        Course("Biology 102"),
        Course("Biology 102"),
        Course("Biology 102"),
        Course("Biology 102"),
    )
}