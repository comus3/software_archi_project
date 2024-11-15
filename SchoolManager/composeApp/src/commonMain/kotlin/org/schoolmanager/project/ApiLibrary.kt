import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiLibrary {

    private val apiService = ApiClient.apiService

    fun fetchEvents(onSuccess: (List<Event>) -> Unit, onError: (Throwable) -> Unit) {
        apiService.getEvents().enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                response.body()?.let(onSuccess) ?: onError(Exception("No data available"))
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun fetchCalendar(onSuccess: (Calendar) -> Unit, onError: (Throwable) -> Unit) {
        apiService.getCalendar().enqueue(object : Callback<Calendar> {
            override fun onResponse(call: Call<Calendar>, response: Response<Calendar>) {
                response.body()?.let(onSuccess) ?: onError(Exception("No data available"))
            }

            override fun onFailure(call: Call<Calendar>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun fetchCourses(onSuccess: (List<Course>) -> Unit, onError: (Throwable) -> Unit) {
        apiService.getCourses().enqueue(object : Callback<List<Course>> {
            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                response.body()?.let(onSuccess) ?: onError(Exception("No data available"))
            }

            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun fetchStudents(onSuccess: (List<Student>) -> Unit, onError: (Throwable) -> Unit) {
        apiService.getStudents().enqueue(object : Callback<List<Student>> {
            override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                response.body()?.let(onSuccess) ?: onError(Exception("No data available"))
            }

            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun fetchStudent(matricule: String, onSuccess: (Student) -> Unit, onError: (Throwable) -> Unit) {
        apiService.getStudent(matricule).enqueue(object : Callback<Student> {
            override fun onResponse(call: Call<Student>, response: Response<Student>) {
                response.body()?.let(onSuccess) ?: onError(Exception("No data available"))
            }

            override fun onFailure(call: Call<Student>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun fetchGrades(matricule: String, onSuccess: (Grades) -> Unit, onError: (Throwable) -> Unit) {
        apiService.getGrades(matricule).enqueue(object : Callback<Grades> {
            override fun onResponse(call: Call<Grades>, response: Response<Grades>) {
                response.body()?.let(onSuccess) ?: onError(Exception("No data available"))
            }

            override fun onFailure(call: Call<Grades>, t: Throwable) {
                onError(t)
            }
        })
    }
}
