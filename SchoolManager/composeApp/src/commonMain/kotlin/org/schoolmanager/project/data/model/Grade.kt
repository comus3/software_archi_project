// data.model/Grade.kt
package org.schoolmanager.project.data.model
import kotlinx.serialization.Serializable

//@Serializable
//data class StudentGradesResponse(
//    val student_grades: List<StudentGrade> // Liste des étudiants et leurs notes
//)

@Serializable
data class StudentGrade(
    val student_id: String, // ID de l'étudiant
    val grades: List<StudentCourse> // Liste des cours et sous-cours
)

@Serializable
data class StudentCourse(
    val course: String, // Nom du cours principal
    val final_grades: GradeDetails, // Notes finales du cours principal
    val subgrades: List<SubGrade> // Liste des sous-cours
)

@Serializable
data class SubGrade(
    val subcourse: String, // Nom du sous-cours
    val grades: GradeDetails // Notes mensuelles du sous-cours
)

@Serializable
data class GradeDetails(
    val jan: String?, // Note en janvier
    val jun: String?, // Note en juin
    val sept: String? // Note en septembre
)




//@Serializable
//data class Grade(
//    val subject: String,
//    val jan: String,
//    val jun: String,
//    val sept: String
//)
//@Serializable
//data class SubGrade(
//    val subject: String,
//    val jan: String,
//    val jun: String,
//    val sept: String
//)