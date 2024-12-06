package org.schoolmanager.project.viewmodel

import androidx.lifecycle.ViewModel
import org.schoolmanager.project.data.model.NewsHomePage
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import org.schoolmanager.project.ApiService

class NewsViewModel: ViewModel(){
    private val coroutineScope= CoroutineScope(SupervisorJob() +Dispatchers.Default)
    val news= MutableStateFlow<List<NewsHomePage>>(emptyList())

    //FCT TO HAVE NEWS FROM THE API
    fun fetchNews() {
        coroutineScope.launch {
            val fetchedNews = ApiService.fetchNews()
            news.value = fetchedNews
        }
    }
}