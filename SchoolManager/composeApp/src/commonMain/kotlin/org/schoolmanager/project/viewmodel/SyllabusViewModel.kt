package org.schoolmanager.project.viewmodel

import androidx.lifecycle.ViewModel
import org.schoolmanager.project.data.model.Orientation
import org.schoolmanager.project.data.model.Syllabus


class SyllabusViewModel: ViewModel(){
    val orientations= listOf(
        Orientation(1, "BLOC 1"),
        Orientation(2, "BLOC 2"),
        Orientation(3, "BLOC 3"),
        Orientation(4, "BLOC 4"),
        Orientation(5, "BLOC 5"),
        Orientation(6, "BLOC 6"),
        Orientation(7, "BLOC 7"),
        Orientation(8, "BLOC 8"),
        Orientation(9, "BLOC 9"),
        Orientation(10, "BLOC 10"),


    )

    val syllabus= listOf(
        Syllabus(1, "Chimie Q1",1, 15.99),
        Syllabus(1, "Calculus",1, 15.99),
        Syllabus(1, "Guide du dessinateur",1, 15.99),
        Syllabus(2, "Calculus",1, 15.99),
        Syllabus(2, "Guide du dessinateur",1, 15.99),
        Syllabus(3, "Encyclopedie",1, 15.99),
    )
}