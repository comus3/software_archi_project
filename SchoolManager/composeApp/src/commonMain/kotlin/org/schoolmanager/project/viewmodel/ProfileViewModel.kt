package org.schoolmanager.project.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import org.schoolmanager.project.data.model.Courses

class ProfileViewModel : ViewModel(){
    val courses = mutableStateListOf(
        Courses("Mathematics 101"),
        Courses("Physics 201"),
        Courses("Computer Science 305"),
        Courses("History 210"),
        Courses("Biology 102"),
    )
}