//import retrofit2.Call
//import retrofit2.http.GET
//import retrofit2.http.Path
//
//// Data models
//data class Event(val id: String, val name: String, val description: String)
//data class Calendar(val id: String, val events: List<Event>)
//data class Course(val id: String, val name: String)
//data class Student(val id: String, val name: String, val age: Int)
//data class Grades(val studentId: String, val grades: Map<String, Double>)
//
//// Retrofit interface
//interface ApiService {
//
//    @GET("/events")
//    fun getEvents(): Call<List<Event>>
//
//    @GET("/calendar")
//    fun getCalendar(): Call<Calendar>
//
//    @GET("/courses")
//    fun getCourses(): Call<List<Course>>
//
//    @GET("/students")
//    fun getStudents(): Call<List<Student>>
//
//    @GET("/student/{matricule}")
//    fun getStudent(@Path("matricule") matricule: String): Call<Student>
//
//    @GET("/grades/{matricule}")
//    fun getGrades(@Path("matricule") matricule: String): Call<Grades>
//}
