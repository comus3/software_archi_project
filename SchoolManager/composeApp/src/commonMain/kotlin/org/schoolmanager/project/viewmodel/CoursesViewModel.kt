package org.schoolmanager.project.viewmodel

import androidx.lifecycle.ViewModel
import org.schoolmanager.project.data.model.Course
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.administration_reseau
import schoolmanager.composeapp.generated.resources.alternatif_monophase
import schoolmanager.composeapp.generated.resources.electronic_circuit
import schoolmanager.composeapp.generated.resources.motor

class CoursesViewModel: ViewModel(){
    val courses = listOf(
        Course(1, "Electrical", Res.drawable.alternatif_monophase, "LURKIN Quentin", "lrk@ecam.be"),
        Course(2, "Motors", Res.drawable.motor, "LOUIS Jean-Guillaume", "j3l@ecam.be"),
        Course(3, "Math", Res.drawable.electronic_circuit, "HILLEWAERE Ruben", "hlr@ecam.be"),
        Course(4, "Network", Res.drawable.administration_reseau, "DELHAY Quentin", "dlh@ecam.be"),
    )

    //FCT TO GET THE COURSE BY ID
    fun getCourseById(id: Int): Course?{
        return courses.find{it.id==id}
    }
}