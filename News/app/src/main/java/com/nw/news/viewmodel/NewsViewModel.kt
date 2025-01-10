package com.nw.news.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nw.news.data.local.entity.ArticleEntity
import com.nw.news.data.model.Articles
import com.nw.news.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ViewModel for managing the news articles in the UI.
 * Fetches data from the repository and exposes it to the UI.
 */
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _articles = mutableStateOf<List<ArticleEntity>>(emptyList())
    val articles: State<List<ArticleEntity>> get() = _articles

    init {
        fetchAndSaveArticles()
    }

    fun fetchAndSaveArticles() {
        viewModelScope.launch {
            try {
                repository.fetchAndSaveArticles()
                _articles.value = repository.getSavedArticles()
            } catch (e: Exception) {
                // Handle exceptions (e.g., show an error message in UI)
                e.printStackTrace()
            }
        }
    }

    fun getArticleById(articleId: String?): ArticleEntity? {
        return articles.value.find { it.url == articleId }
    }
}

