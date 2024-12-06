package org.schoolmanager.project.viewmodel

import androidx.lifecycle.ViewModel
import org.schoolmanager.project.data.model.Course
import schoolmanager.composeapp.generated.resources.Res
import schoolmanager.composeapp.generated.resources.administration_reseau
import schoolmanager.composeapp.generated.resources.ai
import schoolmanager.composeapp.generated.resources.alternatif_monophase
import schoolmanager.composeapp.generated.resources.chem
import schoolmanager.composeapp.generated.resources.controlsys
import schoolmanager.composeapp.generated.resources.electronic_circuit
import schoolmanager.composeapp.generated.resources.mechanics
import schoolmanager.composeapp.generated.resources.motor
import schoolmanager.composeapp.generated.resources.physics
import schoolmanager.composeapp.generated.resources.programming
import schoolmanager.composeapp.generated.resources.robotics
import schoolmanager.composeapp.generated.resources.stat
import schoolmanager.composeapp.generated.resources.thermodynamics


class CoursesViewModel: ViewModel(){
    val courses = listOf(
        Course(1, "Electrical", Res.drawable.alternatif_monophase, "LURKIN Quentin", "lrk@ecam.be"),
        Course(2, "Motors", Res.drawable.motor, "LOUIS Jean-Guillaume", "j3l@ecam.be"),
        Course(3, "Math", Res.drawable.electronic_circuit, "HILLEWAERE Ruben", "hlr@ecam.be"),
        Course(4, "Network", Res.drawable.administration_reseau, "DELHAY Quentin", "dlh@ecam.be"),
        Course(5, "Physics", Res.drawable.physics, "VERMEULEN Sophie", "svm@ecam.be"),
        Course(6, "Thermodynamics", Res.drawable.thermodynamics, "DUPONT Alain", "dpt@ecam.be"),
        Course(7, "Programming", Res.drawable.programming, "MARTIN Claire", "cmr@ecam.be"),
        Course(8, "Mechanics", Res.drawable.mechanics, "GIRARD Eric", "grd@ecam.be"),
        Course(9, "Robotics", Res.drawable.robotics, "LEGRAND Nicolas", "nl@ecam.be"),
        Course(10, "Artificial Intelligence", Res.drawable.ai, "PEETERS Marie", "mpr@ecam.be"),
        Course(11, "Statistics", Res.drawable.stat, "VANDENBERG Hugo", "hvd@ecam.be"),
        Course(12, "Chemistry", Res.drawable.chem, "LAMBERT Julie", "jlb@ecam.be"),
        Course(13, "Control Systems", Res.drawable.controlsys, "BERTRAND Paul", "pbt@ecam.be"),
    )


    //FCT TO GET THE COURSE BY ID
    fun getCourseById(id: Int): Course?{
        return courses.find{it.id==id}
    }
}