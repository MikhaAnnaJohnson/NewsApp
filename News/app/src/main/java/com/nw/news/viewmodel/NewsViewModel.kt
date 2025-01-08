package com.nw.news.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nw.news.data.model.Articles
import com.nw.news.data.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    // Replace LiveData with State for Compose compatibility
    private val _news = mutableStateOf<List<Articles>>(emptyList())
    val news: State<List<Articles>> get() = _news

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                val response = repository.getTopHeadlines()
                if (response.isSuccessful) {
                    _news.value = response.body()?.articles ?: emptyList()
                } else {
                    // Handle error (optional)
                    _news.value = emptyList()
                }
            } catch (e: Exception) {
                // Handle network or other errors
                _news.value = emptyList()
            }
        }
    }
}

