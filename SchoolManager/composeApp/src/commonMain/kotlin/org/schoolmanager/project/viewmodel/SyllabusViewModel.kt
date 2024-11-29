package org.schoolmanager.project.viewmodel

import androidx.lifecycle.ViewModel
import org.schoolmanager.project.data.model.Orientation
import org.schoolmanager.project.data.model.Syllabus


class SyllabusViewModel: ViewModel(){
    val orientations= listOf(
        Orientation(1, "Bloc 1"),
        Orientation(2, "Bloc 2"),
        Orientation(3, "Bloc 3"),
        Orientation(4, "Bloc 4"),
    )

    val syllabus= listOf(
        Syllabus(1, "Chimie Q1"),
        Syllabus(2, "Calculus"),
        Syllabus(2, "Guide du dessinateur"),
        Syllabus(4, "Encyclopedie"),
    )
}