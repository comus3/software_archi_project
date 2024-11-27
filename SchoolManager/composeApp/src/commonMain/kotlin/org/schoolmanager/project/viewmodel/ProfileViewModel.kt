package org.schoolmanager.project.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import org.schoolmanager.project.data.model.Course

class ProfileViewModel : ViewModel(){
    val courses = mutableStateListOf(
        Course(name= "Mathematics 101"),
        Course(name= "Physics 201"),
        Course(name= "Computer Science 305"),
    )
}